package com.vivant.annecharlotte.mynews;

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
            worldCheckbox;

    private MenuItem nav1,
    nav2,
    nav3,
    nav4;

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
    worldIndex;

    private boolean launch;

    int index =0;

    private String TAG = "PERSO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalization);
        configureLayoutLinks();
        configureNotificationToolbar();
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

        nav1 = (MenuItem) findViewById(R.id.nav_1);
        nav2 = (MenuItem) findViewById(R.id.nav_2);
        nav3 = (MenuItem) findViewById(R.id.nav_3);
        nav4 = (MenuItem) findViewById(R.id.nav_4);
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
                    updateKeys();
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

    public void updateKeys() {
        // mise à jour de l'affichage
        int sumIndex = artsIndex + businessIndex + politicsIndex + sportIndex + travelIndex + fashionIndex + foodIndex + scienceIndex + technologyIndex+ worldIndex;

        //java.lang.NullPointerException: Attempt to invoke interface method 'android.view.MenuItem android.view.MenuItem.setVisible(boolean)' on a null object reference

        nav1.setVisible(false);
        nav2.setVisible(false);
        nav3.setVisible(false);
        nav4.setVisible(false);

        switch (sumIndex) {
            case 1:
                personalization(nav1);
            case 2:
                personalization(nav2);
            case 3:
                personalization(nav3);
            case 4:
                personalization(nav4);
                break;
        }
    }

    public int indexComptabilization() {
        artsIndex = businessIndex= politicsIndex= sportIndex= travelIndex= fashionIndex= foodIndex= scienceIndex = technologyIndex= worldIndex=0;
        index=0;

        if (politicsCheckbox.isChecked()) {
            index += 1;
            politicsIndex =1; }
        if (artsCheckbox.isChecked()) {
            index += 1;
        artsIndex = 1;}
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


    public void personalization(MenuItem nav) {
        nav.setVisible(true);

        if (politicsIndex ==1) {
            nav.setTitle(R.string.politics_checkbox_text);
            nav.setIcon(R.drawable.baseline_sentiment_very_dissatisfied_24);
            politicsIndex =0;
        }

        if (artsIndex ==1) {
            nav.setTitle(R.string.arts_checkbox_text);
            nav.setIcon(R.drawable.baseline_help_24);
            artsIndex =0;
        }

        if (businessIndex ==1) {
            nav.setTitle(R.string.business_checkbox_text);
            nav.setIcon(R.drawable.baseline_sentiment_very_dissatisfied_24);
            businessIndex =0;
        }

        if (sportIndex ==1) {
            nav.setTitle(R.string.sport_checkbox_text);
            nav.setIcon(R.drawable.baseline_local_florist_24);
            sportIndex =0;
        }

        if (fashionIndex ==1) {
            nav.setTitle(R.string.fashion_checkbox_text);
            nav.setIcon(R.drawable.baseline_face_24);
            fashionIndex =0;
        }

        if (foodIndex ==1) {
            nav.setTitle(R.string.food_checkbox_text);
            nav.setIcon(R.drawable.baseline_local_dining_24);
            foodIndex =0;
        }

        if (scienceIndex ==1) {
            nav.setTitle(R.string.sciences_checkbox_text);
            nav.setIcon(R.drawable.baseline_blur_linear_24);
            artsIndex =0;
        }

        if (technologyIndex ==1) {
            nav.setTitle(R.string.technology_checkbox_text);
            nav.setIcon(R.drawable.baseline_wb_iridescent_24);
            technologyIndex =0;
        }

        if (travelIndex ==1) {
            nav.setTitle(R.string.travel_checkbox_text);
            nav.setIcon(R.drawable.baseline_notification_important_24);
            travelIndex =0;
        }
    }

}
