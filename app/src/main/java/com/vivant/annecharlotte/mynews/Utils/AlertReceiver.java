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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vivant.annecharlotte.mynews.API.ApiKey;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIClient;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIInterface;
import com.vivant.annecharlotte.mynews.Models.NYTSearchArticles;
import com.vivant.annecharlotte.mynews.R;
import com.vivant.annecharlotte.mynews.ResultsSearchNotification;
import com.vivant.annecharlotte.mynews.Views.ListOfSearchedArticlesAdapter;
import com.vivant.annecharlotte.mynews.WebViewActivity;

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
    private String textMessage;
    int newArticles = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        //lancer la recherche

        /*mResultsSearchNotification = new ResultsSearchNotification();
        newArticles = mResultsSearchNotification.getNumberArticles();*/

        if (newArticles > 0) {
            textMessage = "Vous avez " + newArticles + "  nouveaux articles à découvrir!";
        } else {
            textMessage = "Vous n'avez aucun article à découvrir!";
        }
        // Envoyer la notification s'il y a des résultats
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification("MyNews", textMessage);
        notificationHelper.getManager().notify(1, nb.build());
        }


}
