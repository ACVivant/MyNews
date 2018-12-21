package com.vivant.annecharlotte.mynews;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;


public class ResultsSearchActivity extends AppCompatActivity {

    private ResultSearchFragment mResultSearchFragment;

    private String TAG= "resultsearchactivity_zut";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_search);
        this.configureSearchToolbar();
        this.configureAndShowResultSearchFragment();

        Log.d(TAG, "onCreate: ");

    }

    private void configureAndShowResultSearchFragment() {
        Log.d(TAG, "configureAndShowResultSearchFragment: ");
        mResultSearchFragment = (ResultSearchFragment) getSupportFragmentManager().findFragmentById(R.id.container_search_recyclerview);

        if (mResultSearchFragment == null) {
            mResultSearchFragment = new ResultSearchFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_search_recyclerview, mResultSearchFragment)
                    .commit();
        }
    }

    private void configureSearchToolbar() {
        Log.d(TAG, "configureSearchToolbar: ");
        //Get the toolbar (Serialise)
        Toolbar searchToolbar = (Toolbar) findViewById(R.id.search_toolbar);
        //Set the toolbar
        setSupportActionBar(searchToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

}
