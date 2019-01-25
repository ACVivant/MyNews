package com.vivant.annecharlotte.mynews.Views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;

/**
 * Generates Popup with adapted text.
 */
public class Popup {
    private int title;
    private int message;
    private int icon;
    private Context context;

    public Popup(Context context, int title, int message, int icon) {
        this.context = context;
        this.title = title;
        this.message = message;
        this.icon = icon;
    }

    public void personnaliseAndLaunchPopup() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(icon)
                .show();
    }
}
