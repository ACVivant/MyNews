package com.vivant.annecharlotte.mynews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Anne-Charlotte Vivant on 14/12/2018.
         */
public class SearchActivity extends AppCompatActivity {

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

    }
}
