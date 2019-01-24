package com.vivant.annecharlotte.mynews.Controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.vivant.annecharlotte.mynews.R;

public class PersonalizationActivity extends AppCompatActivity {
    private Switch switchButton;
    private CheckBox artsCheckbox,
            businessCheckbox,
            politicsCheckbox,
            sportCheckbox,
            travelCheckbox,
            fashionCheckbox,
            foodCheckbox,
            scienceCheckbox,
            technologyCheckbox,
            worldCheckbox,
    healthCheckbox;

    private EditText textNotif;

    private int artsIndex,
    businessIndex,
            politicsIndex,
    sportIndex,
    travelIndex,
    fashionIndex,
    foodIndex,
    scienceIndex,
    technologyIndex,
    worldIndex,
    healthIndex;

    private boolean launch;

    int index =0;

    private String TAG = "PERSO";
    public static final String SHARED_PREFS = "SharedPrefsPerso";

    public static final String ARTS = "ArtsND";
    public static final String HEALTH = "HealthND";
    public static final String FOOD = "FOodND";
    public static final String TECH = "TechnologyND";
    public static final String SCIENCES = "SciencesND";
    public static final String BUSINESS = "BusinessND";
    public static final String FASHION = "FashionND";
    public static final String POLITICS = "PoliticsND";
    public static final String SPORT = "SportND";
    public static final String TRAVEL = "TravelND";
    public static final String WORLD = "WorldND";

    private boolean artsOnOff;
    private boolean healthOnOff;
    private boolean foodOnOff;
    private boolean technologyOnOff;
    private boolean sciencesOnOff;
    private boolean businessOnOff;
    private boolean fashionOnOff;
    private boolean politicsOnOff;
    private boolean sportOnOff;
    private boolean travelOnOff;
    private boolean worldOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalization);
        configureLayoutLinks();
        configureNotificationToolbar();
        loadData();
        updateView();
    }

    private void configureLayoutLinks() {
        artsCheckbox = findViewById(R.id.art_checkBox);
        sportCheckbox = findViewById(R.id.sport_checkBox);
        businessCheckbox = findViewById(R.id.business_checkBox);
        travelCheckbox = findViewById(R.id.travel_checkBox);
        politicsCheckbox = findViewById(R.id.politics_checkBox);
        fashionCheckbox = findViewById(R.id.fashion_checkBox);
        foodCheckbox = findViewById(R.id.food_checkBox);
        travelCheckbox = findViewById(R.id.travel_checkBox);
        scienceCheckbox = findViewById(R.id.science_checkBox);
        technologyCheckbox = findViewById(R.id.technology_checkBox);
        worldCheckbox = findViewById(R.id.world_checkBox);
        healthCheckbox = findViewById(R.id.health_checkBox);
    }

    private void configureNotificationToolbar() {
        Toolbar notificationToolbar;
        //Get the toolbar (Serialise)
        notificationToolbar = findViewById(R.id.search_toolbar);
        notificationToolbar.setTitle(R.string.personalization_window_title);
        //Set the toolbar
        setSupportActionBar(notificationToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        notificationToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();

                // Save data only if values are ok (launch == true)
                if (launch) {
                    saveData();
                    Intent intent = new Intent();
                    intent.putExtra("keyArts", artsIndex);
                    intent.putExtra("keyBusiness", businessIndex);
                    intent.putExtra("keyFashion", fashionIndex);
                    intent.putExtra("keyFood", foodIndex);
                    intent.putExtra("keyPolitics", politicsIndex);
                    intent.putExtra("keyTravel", travelIndex);
                    intent.putExtra("keySciences", scienceIndex);
                    intent.putExtra("keySport", sportIndex);
                    intent.putExtra("keyTechnology", technologyIndex);
                    intent.putExtra("keyWorld", worldIndex);
                    intent.putExtra("keyHealth", healthIndex);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private boolean validation() {
        index = indexComptabilization();
        launch = true;
        Log.d(TAG, "validation: index " + index);

        if (index == 0) {
            launch = false;
            Toast.makeText(this, R.string.personalization_error0, Toast.LENGTH_LONG).show();
                   }

            if (index > 4) {
                launch = false;
                Toast.makeText(this, R.string.personalization_error4, Toast.LENGTH_LONG).show();
            }
        return launch;
    }

    public int indexComptabilization() {
        artsIndex = businessIndex= politicsIndex= sportIndex= travelIndex= fashionIndex= foodIndex= scienceIndex = technologyIndex= worldIndex= healthIndex =0;
        index=0;

        if (politicsCheckbox.isChecked()) {
            index += 1;
            politicsIndex =1; }
        if (artsCheckbox.isChecked()) {
            index += 1;
            artsIndex = 1;}
        if (healthCheckbox.isChecked()) {
            index += 1;
            healthIndex = 1;}
        if (businessCheckbox.isChecked()) {
            index += 1;
        businessIndex = 1;}
        if (sportCheckbox.isChecked()) {
            index += 1;
        sportIndex = 1; }
        if (fashionCheckbox.isChecked()) {
            index += 1;
        fashionIndex = 1;}
        if (foodCheckbox.isChecked()) {
            index += 1;
        foodIndex = 1;}
        if (scienceCheckbox.isChecked()) {
            index += 1;
        scienceIndex = 1;}
        if (technologyCheckbox.isChecked()) {
            index += 1;
        technologyIndex = 1;}
        if (worldCheckbox.isChecked()) {
            index += 1;
        worldIndex = 1;}
        if (travelCheckbox.isChecked()) {
            index += 1;
        travelIndex = 1;}

        return index;
    }

    public void saveData() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(ARTS, artsCheckbox.isChecked());
        editor.putBoolean(HEALTH, healthCheckbox.isChecked());
        editor.putBoolean(FOOD, foodCheckbox.isChecked());
        editor.putBoolean(TECH, technologyCheckbox.isChecked());
        editor.putBoolean(SCIENCES, scienceCheckbox.isChecked());
        editor.putBoolean(BUSINESS, businessCheckbox.isChecked());
        editor.putBoolean(POLITICS, politicsCheckbox.isChecked());
        editor.putBoolean(SPORT, sportCheckbox.isChecked());
        editor.putBoolean(FASHION, fashionCheckbox.isChecked());
        editor.putBoolean(TRAVEL, travelCheckbox.isChecked());
        editor.putBoolean(WORLD, worldCheckbox.isChecked());

        editor.apply();
    }

    public void loadData() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        artsOnOff = sharedPreferences.getBoolean(ARTS, false);
        healthOnOff = sharedPreferences.getBoolean(HEALTH, false);
        foodOnOff = sharedPreferences.getBoolean(FOOD, false);
        technologyOnOff = sharedPreferences.getBoolean(TECH, false);
        sciencesOnOff = sharedPreferences.getBoolean(SCIENCES, false);
        businessOnOff = sharedPreferences.getBoolean(BUSINESS, false);
        politicsOnOff = sharedPreferences.getBoolean(POLITICS, false);
        sportOnOff = sharedPreferences.getBoolean(SPORT, false);
        fashionOnOff = sharedPreferences.getBoolean(FASHION, false);
        travelOnOff = sharedPreferences.getBoolean(TRAVEL, false);
        worldOnOff = sharedPreferences.getBoolean(WORLD, false);

    }

    public void updateView() {

        artsCheckbox.setChecked(artsOnOff);
        healthCheckbox.setChecked(healthOnOff);
        foodCheckbox.setChecked(foodOnOff);
        technologyCheckbox.setChecked(technologyOnOff);
        scienceCheckbox.setChecked(sciencesOnOff);
        businessCheckbox.setChecked(businessOnOff);
        politicsCheckbox.setChecked(politicsOnOff);
        sportCheckbox.setChecked(sportOnOff);
        fashionCheckbox.setChecked(fashionOnOff);
        travelCheckbox.setChecked(travelOnOff);
        worldCheckbox.setChecked(worldOnOff);
    }

}
