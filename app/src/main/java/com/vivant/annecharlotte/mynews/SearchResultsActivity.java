package com.vivant.annecharlotte.mynews;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Generate results for search demand
 * and show them
 */
public class ResultsSearchActivity extends AppCompatActivity {

    private NYTSearchPageFragment mResultSearchFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_search);
        this.configureSearchToolbar();
        this.configureAndShowResultSearchFragment();
    }

    private void configureAndShowResultSearchFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("q", getIntent().getStringExtra("q"));
        bundle.putString("fq", getIntent().getStringExtra("fq"));
        bundle.putString("begin_date", getIntent().getStringExtra("begin_date"));
        bundle.putString("end_date", getIntent().getStringExtra("end_date"));

        mResultSearchFragment = (NYTSearchPageFragment) getSupportFragmentManager().findFragmentById(R.id.container_search_recyclerview);

        if (mResultSearchFragment == null) {
            mResultSearchFragment = new NYTSearchPageFragment();
            mResultSearchFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_search_recyclerview, mResultSearchFragment)
                    .commit();
        }
    }

    private void configureSearchToolbar() {
        //Get the toolbar (Serialise)
        Toolbar searchToolbar = (Toolbar) findViewById(R.id.search_toolbar);
        searchToolbar.setTitle(R.string.search_results_title);
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
