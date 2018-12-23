package com.vivant.annecharlotte.mynews;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TimePicker;

import com.vivant.annecharlotte.mynews.Utils.AlertReceiver;
import com.vivant.annecharlotte.mynews.Utils.NotificationHelper;

import java.util.Calendar;

/**
 * Created by Anne-Charlotte Vivant on 14/12/2018.
 */
public class NotificationActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    
    final static String TAG = "notif_zut";

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


    private String mFQuery ;
    private String mQuery;

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

    private NotificationHelper mNotificationHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_notif_window);
        this.configureSearchToolbar();
        this.configureWindow();
        loadData();
        updateView();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
        sendOnChannel("Bravo", textNotif.getText().toString());
    }

    //---------------------------------------------------------------------------------------------------------
    // about notification window
    //---------------------------------------------------------------------------------------------------------

    private void configureSearchToolbar() {
        //Get the toolbar (Serialise)
        Toolbar searchToolbar = (Toolbar) findViewById(R.id.search_toolbar);
        //Set the toolbar
        setSupportActionBar(searchToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void configureWindow() {

        searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setVisibility(View.GONE);

        beginDate = (LinearLayout) findViewById(R.id.begin_date);
        beginDate.setVisibility(View.GONE);

        endDate = (LinearLayout) findViewById(R.id.end_date);
        endDate.setVisibility(View.GONE);

        artsCheckbox = findViewById(R.id.art_checkBox);
        sportCheckbox = findViewById(R.id.sport_checkBox);
        businessCheckbox = findViewById(R.id.business_checkBox);
        entrepreneursCheckbox = findViewById(R.id.entrepreneurs_checkBox);
        travelCheckbox = findViewById(R.id.travel_checkBox);
        politicsCheckbox = findViewById(R.id.politics_checkBox);

        switchButton = (Switch) findViewById(R.id.switch_notification);

        textNotif = (EditText) findViewById(R.id.search_query_edittext);

        mNotificationHelper = new NotificationHelper(this);

    }
    //--------------------------------------------------------------------------------------------------------------
    // about notification channel
    //--------------------------------------------------------------------------------------------------------------
    public void sendOnChannel(String title, String message) {
        NotificationCompat.Builder nb = mNotificationHelper.getChannelNotification(title, message);
        mNotificationHelper.getManager().notify(1, nb.build());

    }

    /* Quelque part il va falloir mettre:
    sendOnChannel(title, message)

     */
    //--------------------------------------------------------------------------------------------------------------
    // about Alarm Manager
    //---------------------------------------------------------------------------------------------------------------

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        //makeAResearch(calendar);
        startAlarm(calendar);
    }

    public void startAlarm(Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
    }

    //---------------------------------------------------------------------------------------------------------------
    // restore and save notification criterion
    //--------------------------------------------------------------------------------------------------------------
    public void saveData() {
        Log.d(TAG, "saveData: ");

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
        Log.d(TAG, "loadData: ");
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
        Log.d(TAG, "updateView: ");
        switchButton.setChecked(switchOnOff);

        artsCheckbox.setChecked(artsOnOff);
        businessCheckbox.setChecked(businessOnOff);
        sportCheckbox.setChecked(sportOnOff);
        entrepreneursCheckbox.setChecked(entrepreneursOnOff);
        travelCheckbox.setChecked(travelOnOff);
        politicsCheckbox.setChecked(politicsOnOff);

        textNotif.setText(textNotifSaved);

    }
}