package com.vivant.annecharlotte.mynews.views;

import android.content.Context;

import com.vivant.annecharlotte.mynews.R;

/**
 * Update text of the notification
 */
public class TextNotif {

    public String createMessage(Context context, int numberArticles) {
        if (numberArticles == 0) {
            return context.getResources().getString(R.string.notification_noarticles);
        } else if (numberArticles == 1) {
            return context.getResources().getString(R.string.notification_newarticles21);
        } else
            return context.getResources().getString(R.string.notification_newarticles1) + numberArticles + context.getResources().getString(R.string.notification_newarticles2);
    }
}
