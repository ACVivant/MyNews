package com.vivant.annecharlotte.mynews.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.vivant.annecharlotte.mynews.Models.Doc;
import com.vivant.annecharlotte.mynews.ResultsSearchNotification;

import java.util.List;

/**
 * Created by Anne-Charlotte Vivant on 21/12/2018.
 */
public class AlertReceiver extends BroadcastReceiver {

    final static String TAG = "ALERT_RECEIVER";
    ResultsSearchNotification mResultsSearchNotification;
    private String textMessage = "";
    int numberArticles = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        search(context);
        textMessage =  createMessage(numberArticles);
        send(context);
        // faire en sorte que tout se déroule dans l'ordre!!!
    }

    public String createMessage(int numberArticles) {
        if (numberArticles == 0) {
            return "Vous n'avez aucun article à découvrir aujourd'hui!";
        } else {
            return "Vous avez " + numberArticles + " nouveaux articles à découvrir aujourd'hui!";
        }
    }

    public void search(Context context) {
        Log.d(TAG, "search: juste avant la recherche de nouveaux articles");
        mResultsSearchNotification = new ResultsSearchNotification(context);
        numberArticles = mResultsSearchNotification.getNumberArticles();
        Log.d(TAG, "search: numberArticles" + numberArticles);
        Log.d(TAG, "search: juste après la recherche de nouveaux articles");
    }

    public void send(Context context) {
        // Envoyer la notification
            Log.d(TAG, "send: envoi de la notification");
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification("MyNews", textMessage);
        notificationHelper.getManager().notify(1, nb.build());
    }

}
