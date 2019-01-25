package com.vivant.annecharlotte.mynews.Controller;

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
import com.vivant.annecharlotte.mynews.Utils.SearchKeysValidation;
import com.vivant.annecharlotte.mynews.Utils.NotificationHelper;
import com.vivant.annecharlotte.mynews.Views.TextNotif;

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
public class NotificationResults {

    public static final String TAG_API = "SEARCH";

    private List<Doc> mListArticles;

    private String mFQuery ;
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

    public void setArtsOnOff(boolean artsOnOff) {
        this.artsOnOff = artsOnOff;
    }

    public void setBusinessOnOff(boolean businessOnOff) {
        this.businessOnOff = businessOnOff;
    }

    public void setEntrepreneursOnOff(boolean entrepreneursOnOff) {
        this.entrepreneursOnOff = entrepreneursOnOff;
    }

    public void setPoliticsOnOff(boolean politicsOnOff) {
        this.politicsOnOff = politicsOnOff;
    }

    public void setSportOnOff(boolean sportOnOff) {
        this.sportOnOff = sportOnOff;
    }

    public void setTravelOnOff(boolean travelOnOff) {
        this.travelOnOff = travelOnOff;
    }


    public boolean isArtsOnOff() {
        return artsOnOff;
    }

    public boolean isBusinessOnOff() {
        return businessOnOff;
    }

    public boolean isEntrepreneursOnOff() {
        return entrepreneursOnOff;
    }

    public boolean isPoliticsOnOff() {
        return politicsOnOff;
    }

    public boolean isSportOnOff() {
        return sportOnOff;
    }

    public boolean isTravelOnOff() {
        return travelOnOff;
    }

    private boolean artsOnOff;
    private boolean businessOnOff;
    private boolean entrepreneursOnOff;
    private boolean politicsOnOff;
    private boolean sportOnOff;
    private boolean travelOnOff;

    public NotificationResults(Context context) {
        // Required empty public constructor
        loadNotifKeys(context);
        searchArticles(context);
    }

    // load notification keys which were saved in SharedPreferences
    public void loadNotifKeys(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        loadSharedPreferences(prefs);

        SearchKeysValidation keywordsValidation = new SearchKeysValidation(mQuery);
        mQuery = keywordsValidation.keywordFormat(mQuery);

        mFQuery =formatTextFQuery();

        formatDate();
    }

    // loas SharedPreferences (which keywords and themes user as choosen)
    public void loadSharedPreferences(SharedPreferences prefs) {
        mQuery = prefs.getString(QUERY, "");
        artsOnOff = prefs.getBoolean(ARTS, false);
        businessOnOff =prefs.getBoolean(BUSINESS, false);
        sportOnOff = prefs.getBoolean(SPORT, false);
        entrepreneursOnOff = prefs.getBoolean(ENTREPRENEURS, false);
        travelOnOff = prefs.getBoolean(TRAVEL, false);
        politicsOnOff = prefs.getBoolean(POLITICS, false);
    }

    // put the text query on the good format for request
    public String formatTextFQuery() {
        String query =  "news_desk:(";
        if (artsOnOff) {query += "\"arts\" ";}
        if (businessOnOff) {query += "\"business\" ";}
        if (sportOnOff) {query += "\"sport\" ";}
        if (entrepreneursOnOff) {query += "\"entrepreneurs\" ";}
        if (travelOnOff) {query += "\"travel\" ";}
        if (politicsOnOff) {query += "\"politics\" ";}
        query+= ")";

        return query;
    }

    // put the date in the good format for request
    public void formatDate() {
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
                textMessage = new TextNotif().createMessage(context,numberArticles);
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
