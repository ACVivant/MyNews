package com.vivant.annecharlotte.mynews.controller;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vivant.annecharlotte.mynews.R;

/**
 * Generates results for search demand
 * and shows them
 */
public class SearchResultsActivity extends AppCompatActivity {

    private NYTSearchPageFragment mResultSearchFragment;

    public static final String KEYWORD_QUERY = "q";
    public static final String NEWSDESK_QUERY = "fq";
    public static final String BEGIN_DATE = "begin_date";
    public static final String END_DATE = "end_date";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_search);
        this.configureSearchToolbar();
        this.configureAndShowResultSearchFragment();
    }

    private void configureAndShowResultSearchFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(KEYWORD_QUERY, getIntent().getStringExtra(KEYWORD_QUERY));
        bundle.putString(NEWSDESK_QUERY, getIntent().getStringExtra(NEWSDESK_QUERY));
        bundle.putString(BEGIN_DATE, getIntent().getStringExtra(BEGIN_DATE));
        bundle.putString(END_DATE, getIntent().getStringExtra(END_DATE));

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
