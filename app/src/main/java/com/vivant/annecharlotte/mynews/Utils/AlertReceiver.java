package com.vivant.annecharlotte.mynews.Utils;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vivant.annecharlotte.mynews.API.ApiKey;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIClient;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIInterface;
import com.vivant.annecharlotte.mynews.Models.Doc;
import com.vivant.annecharlotte.mynews.Models.NYTSearchArticles;
import com.vivant.annecharlotte.mynews.R;
import com.vivant.annecharlotte.mynews.ResultsSearchNotification;
import com.vivant.annecharlotte.mynews.Views.ListOfSearchedArticlesAdapter;
import com.vivant.annecharlotte.mynews.WebViewActivity;

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
 * Created by Anne-Charlotte Vivant on 21/12/2018.
 */
public class AlertReceiver extends BroadcastReceiver {

    final static String TAG = "ALERT_RECEIVER";
    ResultsSearchNotification mResultsSearchNotification;
    private String textMessage = "";
    int newArticles = 0;

    String mQuery;
    String mFQuery = "news_desk:(";
    String mBeginDate;
    String mEndDate;
    int numberArticles = 0;
    private List<Doc> mListArticles;

    @Override
    public void onReceive(Context context, Intent intent) {
        search();
        send(context);
        // pourquoi send() se deroule-t-il avant search?
    }

    public void search() {
        //--------------------------------------------
        // lancer la recherche - contenu de test // double contenu dans ResultsSearchNotification
        //------------------------------------------

        mQuery = "shutdown";
        mFQuery = "business";

        Date day = new Date();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd", Locale.FRENCH);
        mEndDate = f.format(day);
        calendar.add(Calendar.DATE, -1);
        mBeginDate = f.format(calendar.getTime());

        NYTimesAPIInterface apiService = NYTimesAPIClient.getClient().create(NYTimesAPIInterface.class);
        Call<NYTSearchArticles> call = apiService.loadSearch(ApiKey.NYT_API_KEY, mQuery, mFQuery, "newest", mBeginDate, mEndDate);

        Log.d(TAG, "onResponse:Query " + mQuery);
        Log.d(TAG, "onResponse:FQuery " + mFQuery);
        Log.d(TAG, "onResponse:beginDate " + mBeginDate);
        Log.d(TAG, "onResponse:endDate " + mEndDate);

        call.enqueue(new Callback<NYTSearchArticles>() {
            @Override
            public void onResponse(Call<NYTSearchArticles> call, Response<NYTSearchArticles> response) {
                Log.d(TAG, "configureRecyclerView: onResponse ");
                if (!response.isSuccessful()) {
                    Log.e(TAG, "erreur lors de la réponse de la recherche");
                    return;
                }

                NYTSearchArticles posts = response.body();
                mListArticles = posts.getResponse().getDocs();
                Log.d(TAG, "onResponse: numberArticles " + numberArticles);
                if (!mListArticles.isEmpty()) {
                    numberArticles = mListArticles.size() + 1;
                }
                Log.d(TAG, "onResponse: numberArticles " + numberArticles);
            }

            @Override
            public void onFailure(Call<NYTSearchArticles> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

        //--------------------------------------------------------------------------------------------------------------------------
        /*mResultsSearchNotification = new ResultsSearchNotification();
        newArticles = mResultsSearchNotification.getNumberArticles();*/

    }

    public void send(Context context) {
        Log.d(TAG, "envoi notif: numberArticles " + numberArticles);
        if (numberArticles == 0) {
            textMessage = "Vous n'avez aucun article à découvrir aujourd'hui!";
        } else {
            textMessage = "Vous avez " + numberArticles + "  nouveaux articles à découvrir aujourd'hui!";
        }

        // Envoyer la notification s'il y a des résultats
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification("MyNews", textMessage);
        notificationHelper.getManager().notify(1, nb.build());
    }



}
