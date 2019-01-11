package com.vivant.annecharlotte.mynews;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.vivant.annecharlotte.mynews.API.ApiKey;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIClient;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIInterface;
import com.vivant.annecharlotte.mynews.Models.Doc;
import com.vivant.annecharlotte.mynews.Models.NYTSearchArticles;
import com.vivant.annecharlotte.mynews.Utils.NotificationHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Analyse result of the search on notfication keys
 */
public class ResultsSearchNotification {

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
        loadNotifKeys(context);
        searchArticles(context);
    }

    // load notification keys which were saved in SharedPreferences
    public void loadNotifKeys(Context context) {

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

        // format date (today and yesterday)
        Date day = new Date();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd", Locale.FRENCH);
        mEndDate = f.format(day);
        calendar.add(Calendar.DATE, -1);
        mBeginDate = f.format(calendar.getTime());
    }

    // call the Search API
    public void searchArticles(final Context context) {

        NYTimesAPIInterface apiService = NYTimesAPIClient.getClient().create(NYTimesAPIInterface.class);
        Call<NYTSearchArticles> call = apiService.loadSearch(ApiKey.NYT_API_KEY,mQuery, mFQuery,  "newest", mBeginDate, mEndDate);

        call.enqueue(new Callback<NYTSearchArticles>() {
            @Override
            public void onResponse(Call<NYTSearchArticles> call, Response<NYTSearchArticles> response) {
                //AlertReceiver mAlert = new AlertReceiver();
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
                // adjust the text of the notification
                textMessage = new TextNotif().createMessage(numberArticles);
                send(context);
            }

            @Override
            public void onFailure(Call<NYTSearchArticles> call, Throwable t) {
                Log.e(TAG_API, t.toString());
            }
        });
    }

    // Send the notification
    private void send(Context context) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification("MyNews", textMessage);
        notificationHelper.getManager().notify(1, nb.build());
    }
}
