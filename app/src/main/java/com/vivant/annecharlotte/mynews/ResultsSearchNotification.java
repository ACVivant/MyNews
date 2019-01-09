package com.vivant.annecharlotte.mynews;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
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
import com.vivant.annecharlotte.mynews.Views.ListOfSearchedArticlesAdapter;

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
 * Created by Anne-Charlotte Vivant on 08/01/2019.
 */
public class ResultsSearchNotification  extends AppCompatActivity {

    private RelativeLayout mRelativeLayout;
    private RecyclerView mRecyclerView;

    private LinearLayout mArticleItem;
    private String articleUrl;
    private WebViewActivity mArticleWebView = new WebViewActivity();

    public static final String TAG = "resultsearchfragment";
    public static final String TAG_API = "SEARCH";

    public String getTagApi() {
        return TAG_API;
    }

    private ListOfSearchedArticlesAdapter adapter;
    private List<Doc> mListArticles;

    private String mFQuery =  "news_desk:(" ;
    private String mQuery;
    private String mBeginDate ;
    private String mEndDate ;

    private Button searchButton;
    private Switch switchButton;
    private CheckBox artsCheckbox,
            businessCheckbox,
            entrepreneursCheckbox,
            politicsCheckbox,
            sportCheckbox,
            travelCheckbox;
    private EditText textNotif;

    private LinearLayout beginDate,
            endDate;

    public static final String SHARED_PREFS = "SharedPrefs";
    public static final String SWITCH_NOTIF = "SwitchNotif";
    public static final String FQUERY = "FQueryNotif";
    public static final String QUERY = "QueryNotif";
    public static final String ARTS = "ArtsNotif";
    public static final String BUSINESS = "BusinessNotif";
    public static final String ENTREPRENEURS = "EntrepreneursNotif";
    public static final String POLITICS = "PoliticsNotif";
    public static final String SPORT = "SportNotif";
    public static final String TRAVEL = "TravelNotif";

    private String textNotifSaved ;
    private String mQuerySaved;
    private boolean switchOnOff;

    private String FQueryNotif ;
    private String QueryNotif;
    private boolean artsOnOff;
    private boolean businessOnOff;
    private boolean entrepreneursOnOff;
    private boolean politicsOnOff;
    private boolean sportOnOff;
    private boolean travelOnOff;

    public int getNumberArticles() {
        return numberArticles;
    }

    private int numberArticles =0;

    public ResultsSearchNotification() {
        // Required empty public constructor
        loadNotifCriterion();
        searchArticles();
    }

    public void loadNotifCriterion() {

       /* mQuery = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).getString(QUERY, "");
        Log.d(TAG, "loadNotifCriterion: mQuery " + mQuery);

        artsOnOff = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).getBoolean(ARTS, false);
        if (artsOnOff) {mFQuery += "\"arts\" ";}
        businessOnOff = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).getBoolean(BUSINESS, false);
        if (businessOnOff) {mFQuery += "\"business\" ";}
        sportOnOff = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).getBoolean(SPORT, false);
        if (sportOnOff) {mFQuery += "\"sport\" ";}
        entrepreneursOnOff = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).getBoolean(ENTREPRENEURS, false);
        if (entrepreneursOnOff) {mFQuery += "\"entrepreneurs\" ";}
        travelOnOff = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).getBoolean(TRAVEL, false);
        if (travelOnOff) {mFQuery += "\"travel\" ";}
        politicsOnOff = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).getBoolean(POLITICS, false);
        if (politicsOnOff) {mFQuery += "\"politics\" ";}*/

// pour les tests------------------------------------
       mQuery = "shutdown";

       mFQuery = "business";

       //--------------------------------------

        Date day = new Date();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd", Locale.FRENCH);
        mEndDate = f.format(day);
        calendar.add(Calendar.DATE, -1);
        mBeginDate = f.format(calendar.getTime());
    }

    public void searchArticles() {

        NYTimesAPIInterface apiService = NYTimesAPIClient.getClient().create(NYTimesAPIInterface.class);
        Call<NYTSearchArticles> call = apiService.loadSearch(ApiKey.NYT_API_KEY,mQuery, mFQuery,  "newest", mBeginDate, mEndDate);

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
                    numberArticles = mListArticles.size()+1;
                }
                Log.d(TAG, "onResponse: numberArticles " + numberArticles);
            }

            @Override
            public void onFailure(Call<NYTSearchArticles> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}

