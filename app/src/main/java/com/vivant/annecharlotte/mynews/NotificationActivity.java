package com.vivant.annecharlotte.mynews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Anne-Charlotte Vivant on 14/12/2018.
 */
public class NotificationActivity extends AppCompatActivity {

    private Button searchButton;

    private LinearLayout beginDate,
                         endDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_notif_window);
        this.configureSearchToolbar();
        this.configureWindow();
    }

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
    }
}