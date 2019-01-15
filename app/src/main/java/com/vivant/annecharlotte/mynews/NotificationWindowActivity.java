package com.vivant.annecharlotte.mynews;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.vivant.annecharlotte.mynews.Utils.AlertReceiver;

import java.util.Calendar;

/**
 * Generate notification window
 * Save and load notification keys
 */
public class NotificationWindowActivity extends AppCompatActivity {

    private Switch switchButton;
    private CheckBox artsCheckbox,
            businessCheckbox,
            entrepreneursCheckbox,
            politicsCheckbox,
            sportCheckbox,
            travelCheckbox;
    private EditText textNotif;

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
    private boolean switchOnOff;

    private boolean artsOnOff;
    private boolean businessOnOff;
    private boolean entrepreneursOnOff;
    private boolean politicsOnOff;
    private boolean sportOnOff;
    private boolean travelOnOff;

    private boolean launch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_notif_window);
        this.configureNotificationToolbar();
        this.configureWindow();
        loadData();
        updateView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //---------------------------------------------------------------------------------------------------------
    // about notification window
    //---------------------------------------------------------------------------------------------------------

    private void configureNotificationToolbar() {
        Toolbar notificationToolbar;
        //Get the toolbar (Serialise)
        notificationToolbar = findViewById(R.id.search_toolbar);
        notificationToolbar.setTitle("Notifications");
        //Set the toolbar
        setSupportActionBar(notificationToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        notificationToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keywordResult();
                checkboxResult();

                // Save data only if values are ok (launch == true) and switchbutton is on
                if (launch) {
                    saveData();
                    if (switchButton.isChecked()) {
                        startAlarm();
                    } else {
                        cancelAlarm();
                    }
                finish();
                }
            }
        });
    }

    private void configureWindow() {
        Button searchButton;
        searchButton = findViewById(R.id.search_button);
        searchButton.setVisibility(View.GONE);

        LinearLayout beginDate,endDate;
        beginDate = findViewById(R.id.begin_date);
        beginDate.setVisibility(View.GONE);

        endDate = findViewById(R.id.end_date);
        endDate.setVisibility(View.GONE);

        artsCheckbox = findViewById(R.id.art_checkBox);
        sportCheckbox = findViewById(R.id.sport_checkBox);
        businessCheckbox = findViewById(R.id.business_checkBox);
        entrepreneursCheckbox = findViewById(R.id.entrepreneurs_checkBox);
        travelCheckbox = findViewById(R.id.travel_checkBox);
        politicsCheckbox = findViewById(R.id.politics_checkBox);

        switchButton = findViewById(R.id.switch_notification);

        textNotif = findViewById(R.id.search_query_edittext);
        }

    //--------------------------------------------------------------------------------------------------------------
    // about Alarm Manager
    //---------------------------------------------------------------------------------------------------------------

    public void startAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        // send notification each day
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),1000 * 60 * 60 * 24 ,pendingIntent);
    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
    }

    //---------------------------------------------------------------------------------------------------------------
    // restore and save notification keys
    //--------------------------------------------------------------------------------------------------------------
    public void saveData() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(QUERY, textNotif.getText().toString());

        editor.putBoolean(ARTS, artsCheckbox.isChecked());
        editor.putBoolean(BUSINESS, businessCheckbox.isChecked());
        editor.putBoolean(SPORT, sportCheckbox.isChecked());
        editor.putBoolean(ENTREPRENEURS, entrepreneursCheckbox.isChecked());
        editor.putBoolean(TRAVEL, travelCheckbox.isChecked());
        editor.putBoolean(POLITICS, politicsCheckbox.isChecked());

        editor.putBoolean(SWITCH_NOTIF, switchButton.isChecked());
        editor.apply();
    }

    public void loadData() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        textNotifSaved = sharedPreferences.getString(QUERY, "");

        artsOnOff = sharedPreferences.getBoolean(ARTS, false);
        businessOnOff = sharedPreferences.getBoolean(BUSINESS, false);
        sportOnOff = sharedPreferences.getBoolean(SPORT, false);
        entrepreneursOnOff = sharedPreferences.getBoolean(ENTREPRENEURS, false);
        travelOnOff = sharedPreferences.getBoolean(TRAVEL, false);
        politicsOnOff = sharedPreferences.getBoolean(POLITICS, false);

        switchOnOff = sharedPreferences.getBoolean(SWITCH_NOTIF, false);
    }

    public void updateView() {

        switchButton.setChecked(switchOnOff);

        artsCheckbox.setChecked(artsOnOff);
        businessCheckbox.setChecked(businessOnOff);
        sportCheckbox.setChecked(sportOnOff);
        entrepreneursCheckbox.setChecked(entrepreneursOnOff);
        travelCheckbox.setChecked(travelOnOff);
        politicsCheckbox.setChecked(politicsOnOff);

        textNotif.setText(textNotifSaved);
    }

    //------------------------------------------------------------------------------------------------
    // format request
    //------------------------------------------------------------------------------------------------
    private void keywordResult() {
        launch = true;
        String keywordsResults1 = textNotif.getText().toString();
        if (keywordsResults1.length() < 1) {
            Toast.makeText(this, R.string.notificationdialog_checkbox_error, Toast.LENGTH_LONG).show();
            launch = false;
        } else {
            keywordFormat(keywordsResults1);
        }
    }

    public String keywordFormat(String str) {
        String keywordsResults;
            keywordsResults = "(";
            String[] splitArray = null;
            // We split String str and put each part of the result in splitArray cell
            splitArray = str.split(" ");

            for (int i = 0; i < splitArray.length; i++) {
                // we create the right string for API
                keywordsResults += "\"" + splitArray[i] + "\" ";
            }
            keywordsResults += ")";

        return keywordsResults;
    }

    private void checkboxResult () {

        String checkboxResults = "news_desk:(";
        int index = 0;

        if (politicsCheckbox.isChecked()) {
            checkboxResults += "\"politics\" ";
            index+=1;
        }

        if (artsCheckbox.isChecked()) {
            checkboxResults += "\"arts\" ";
            index+=1;
        }

        if (businessCheckbox.isChecked()) {
            checkboxResults += "\"business\" ";
            index+=1;
        }

        if (sportCheckbox.isChecked()) {
            checkboxResults += "\"sports\" ";
            index+=1;
        }

        if (entrepreneursCheckbox.isChecked()) {
            checkboxResults += "\"entrepreneurs\" ";
            index+=1;
        }

        if (travelCheckbox.isChecked()) {
            checkboxResults += "\"travel\" ";
            index+=1;
        }

        checkboxResults += ")";

        if(index==0){
            launch = false;
            Toast.makeText(this, "Il faut choisir au moins une catégorie", Toast.LENGTH_LONG).show();
        }
    }

}