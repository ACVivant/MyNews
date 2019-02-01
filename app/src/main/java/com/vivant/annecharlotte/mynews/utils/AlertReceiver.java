package com.vivant.annecharlotte.mynews.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.vivant.annecharlotte.mynews.controller.NotificationResults;

/**
 * Organize what to do when Alarm is on
 */
public class AlertReceiver extends BroadcastReceiver {

    int numberArticles = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        searchAndSendNotif(context);
    }

    public void searchAndSendNotif(Context context) {
        NotificationResults mResultsSearchNotification;
        mResultsSearchNotification = new NotificationResults(context);
        numberArticles = mResultsSearchNotification.getNumberArticles();
        }
}
