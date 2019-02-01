package com.vivant.annecharlotte.mynews.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.vivant.annecharlotte.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Display a window for personalization of Navigation Drawer and save data
 */
public class PersonalizationActivity extends AppCompatActivity {

    @BindView(R.id.art_checkBox) CheckBox artsCheckbox;
    @BindView(R.id.sport_checkBox) CheckBox sportCheckbox;
    @BindView(R.id.business_checkBox) CheckBox businessCheckbox;
    @BindView(R.id.travel_checkBox) CheckBox travelCheckbox;
    @BindView(R.id.politics_checkBox) CheckBox politicsCheckbox;
    @BindView(R.id.fashion_checkBox) CheckBox fashionCheckbox;
    @BindView(R.id.food_checkBox) CheckBox foodCheckbox;
    @BindView(R.id.science_checkBox) CheckBox scienceCheckbox;
    @BindView(R.id.technology_checkBox) CheckBox technologyCheckbox;
    @BindView(R.id.world_checkBox) CheckBox worldCheckbox;
    @BindView(R.id.health_checkBox) CheckBox healthCheckbox;
    @BindView(R.id.search_toolbar) Toolbar notificationToolbar;

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

    private static final String ARTS = "ArtsND";
    private static final String HEALTH = "HealthND";
    private static final String FOOD = "FOodND";
    private static final String TECH = "TechnologyND";
    private static final String SCIENCES = "SciencesND";
    private static final String BUSINESS = "BusinessND";
    private static final String FASHION = "FashionND";
    private static final String POLITICS = "PoliticsND";
    private static final String SPORT = "SportND";
    private static final String TRAVEL = "TravelND";
    private static final String WORLD = "WorldND";

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
        ButterKnife.bind(this);
        configureNotificationToolbar();
        loadData();
        updateView();
    }

    // Toolbar
    private void configureNotificationToolbar() {
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
                index = indexAccounting();
                validation(index);

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

    //-------------------------------------------------------------------------------------------------------
    // Validate and save the user preferences
    //-------------------------------------------------------------------------------------------------------

    // Generate toasts if user has not choosen the good number of themes
    private boolean validation(int ind) {
        launch = true;

        if (ind == 0) {
            launch = false;
            Toast.makeText(this, R.string.personalization_error0, Toast.LENGTH_LONG).show();
        }

        if (ind > 4) {
            launch = false;
            Toast.makeText(this, R.string.personalization_error4, Toast.LENGTH_LONG).show();
        }
        return launch;
    }

    // Count the number of selected themes
    public int indexAccounting() {
        artsIndex = businessIndex= politicsIndex= sportIndex= travelIndex= fashionIndex= foodIndex= scienceIndex = technologyIndex= worldIndex= healthIndex =0;
        int ind=0;

        if (politicsCheckbox.isChecked()) {
            ind += 1;
            politicsIndex =1; }
        if (artsCheckbox.isChecked()) {
            ind += 1;
            artsIndex = 1;}
        if (healthCheckbox.isChecked()) {
            ind += 1;
            healthIndex = 1;}
        if (businessCheckbox.isChecked()) {
            ind += 1;
            businessIndex = 1;}
        if (sportCheckbox.isChecked()) {
            ind += 1;
            sportIndex = 1; }
        if (fashionCheckbox.isChecked()) {
            ind += 1;
            fashionIndex = 1;}
        if (foodCheckbox.isChecked()) {
            ind += 1;
            foodIndex = 1;}
        if (scienceCheckbox.isChecked()) {
            ind += 1;
            scienceIndex = 1;}
        if (technologyCheckbox.isChecked()) {
            ind += 1;
            technologyIndex = 1;}
        if (worldCheckbox.isChecked()) {
            ind += 1;
            worldIndex = 1;}
        if (travelCheckbox.isChecked()) {
            ind += 1;
            travelIndex = 1;}

        return ind;
    }

    // save data in SharedPreferences
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

    //----------------------------------------------------------------------------------------------
    // update the personalization window with user preferences
    //----------------------------------------------------------------------------------------------

    // load SharedPreferences
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

    // update view with saved preferences
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
