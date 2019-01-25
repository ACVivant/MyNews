package com.vivant.annecharlotte.mynews.Controller;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vivant.annecharlotte.mynews.R;

/**
 * Display a window for navigation drawer actuality
 */

public class NavigationDrawerActivity extends AppCompatActivity {

    private NYTPageFragment mResultNDFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_search);
        this.configureSearchToolbar();
        this.configureAndShowResultSearchFragment();
    }

    private void configureAndShowResultSearchFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", getIntent().getIntExtra("pos",0));

        mResultNDFragment = (NYTPageFragment) getSupportFragmentManager().findFragmentById(R.id.container_search_recyclerview);

        if (mResultNDFragment == null) {
            mResultNDFragment = new NYTPageFragment();
            mResultNDFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_search_recyclerview, mResultNDFragment)
                    .commit();
        }
    }

    private void configureSearchToolbar() {
        //Get the toolbar (Serialise)
        Toolbar searchToolbar = (Toolbar) findViewById(R.id.search_toolbar);
        searchToolbar.setTitle(R.string.drawer_results_title);
        //Set the toolbar
        setSupportActionBar(searchToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        searchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
