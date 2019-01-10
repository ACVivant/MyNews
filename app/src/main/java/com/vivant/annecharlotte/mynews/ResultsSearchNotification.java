package com.vivant.annecharlotte.mynews;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vivant.annecharlotte.mynews.API.ApiKey;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIClient;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIInterface;
import com.vivant.annecharlotte.mynews.Models.Doc;
import com.vivant.annecharlotte.mynews.Models.NYTSearchArticles;
import com.vivant.annecharlotte.mynews.Utils.AlertReceiver;
import com.vivant.annecharlotte.mynews.Utils.NotificationHelper;
import com.vivant.annecharlotte.mynews.Views.ListOfSearchedArticlesAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Anne-Charlotte Vivant on 08/01/2019.
 */
public class ResultsSearchNotification {

    public static final String TAG = "resultsearchnotif";
    public static final String TAG_API = "SEARCH";

    private List<Doc> mListArticles;

    private String mFQuery =  "news_desk:(" ;
    private String mQuery;
    private String mBeginDate ;
    private String mEndDate ;

    private static final String SHARED_PREFS = "SharedPrefs";
    private static final String QUERY = "QueryNotif";
    private static final String ARTS = "ArtsNotif";
    private static final String BUSINESS = "BusinessNotif";
    private static final String ENTREPRENEURS = "EntrepreneursNotif";
    private static final String POLITICS = "PoliticsNotif";
    private static final String SPORT = "SportNotif";
    private static final String TRAVEL = "TravelNotif";

    public int getNumberArticles() {
        return numberArticles;
    }

    private int numberArticles =0;
    private String textMessage = "";

    public ResultsSearchNotification(Context context) {
        // Required empty public constructor
        loadNotifCriterion(context);
        searchArticles(context);
    }

    public void loadNotifCriterion(Context context) {

        boolean artsOnOff;
        boolean businessOnOff;
        boolean entrepreneursOnOff;
        boolean politicsOnOff;
        boolean sportOnOff;
        boolean travelOnOff;

        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        mQuery = prefs.getString(QUERY, "");

        artsOnOff = prefs.getBoolean(ARTS, false);
        if (artsOnOff) {mFQuery += "\"arts\" ";}
        businessOnOff =prefs.getBoolean(BUSINESS, false);
        if (businessOnOff) {mFQuery += "\"business\" ";}
        sportOnOff = prefs.getBoolean(SPORT, false);
        if (sportOnOff) {mFQuery += "\"sport\" ";}
        entrepreneursOnOff = prefs.getBoolean(ENTREPRENEURS, false);
        if (entrepreneursOnOff) {mFQuery += "\"entrepreneurs\" ";}
        travelOnOff = prefs.getBoolean(TRAVEL, false);
        if (travelOnOff) {mFQuery += "\"travel\" ";}
        politicsOnOff = prefs.getBoolean(POLITICS, false);
        if (politicsOnOff) {mFQuery += "\"politics\" ";}
        mFQuery+= ")";

        Date day = new Date();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd", Locale.FRENCH);
        mEndDate = f.format(day);
        calendar.add(Calendar.DATE, -1);
        mBeginDate = f.format(calendar.getTime());
    }

    public void searchArticles(final Context context) {

        NYTimesAPIInterface apiService = NYTimesAPIClient.getClient().create(NYTimesAPIInterface.class);
        Call<NYTSearchArticles> call = apiService.loadSearch(ApiKey.NYT_API_KEY,mQuery, mFQuery,  "newest", mBeginDate, mEndDate);

        call.enqueue(new Callback<NYTSearchArticles>() {
            @Override
            public void onResponse(Call<NYTSearchArticles> call, Response<NYTSearchArticles> response) {
                AlertReceiver mAlert = new AlertReceiver();
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                NYTSearchArticles posts = response.body();
                mListArticles = posts.getResponse().getDocs();
                if (!mListArticles.isEmpty()) {
                    numberArticles = mListArticles.size();
                } else {
                    numberArticles =0;
                }
                textMessage = new TextNotif().createMessage(numberArticles);
                send(context);
            }

            @Override
            public void onFailure(Call<NYTSearchArticles> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void send(Context context) {
        // Envoyer la notification
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification("MyNews", textMessage);
        notificationHelper.getManager().notify(1, nb.build());
    }
}
