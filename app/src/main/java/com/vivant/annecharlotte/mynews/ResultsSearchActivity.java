package com.vivant.annecharlotte.mynews;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vivant.annecharlotte.mynews.Models.Doc;
import com.vivant.annecharlotte.mynews.Views.ListOfSearchedArticlesAdapter;

import java.util.List;

public class ResultsSearchActivity extends AppCompatActivity {

    private ResultSearchFragment mResultSearchFragment;

    private String mFQuery ;
    private String mQuery;
    private String mBeginDate ;
    private String mEndDate ;

    private String TAG= "resultsearchactivity_zut";
    private String TAG_API= "SEARCH";

    private ResultSearchFragment fragobj;

    private RecyclerView mRecyclerView;
    private LinearLayout mArticleItem;
    private String articleUrl;
    private WebViewActivity mArticleWebView = new WebViewActivity();

    private ListOfSearchedArticlesAdapter adapter;
    private List<Doc> mListArticles;

    public interface OnArticleClickedListener {
        void onArticletClicked(int position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_search);
        this.configureSearchToolbar();
        this.configureAndShowResultSearchFragment();


        Log.d(TAG, "onCreate: ");

       // configureRecyclerView();
    }


    private void configureAndShowResultSearchFragment() {
        Log.d(TAG, "configureAndShowResultSearchFragment: ");
        Bundle bundle = new Bundle();
        bundle.putString("q", getIntent().getStringExtra("q"));
        bundle.putString("fq", getIntent().getStringExtra("fq"));
        bundle.putString("begin_date", getIntent().getStringExtra("begin_date"));
        bundle.putString("end_date", getIntent().getStringExtra("end_date"));

        mResultSearchFragment = (ResultSearchFragment) getSupportFragmentManager().findFragmentById(R.id.container_search_recyclerview);

        if (mResultSearchFragment == null) {
            mResultSearchFragment = new ResultSearchFragment();
            mResultSearchFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_search_recyclerview, mResultSearchFragment)
                    .commit();
        }
    }

    private void configureSearchToolbar() {
        Log.d(TAG, "configureSearchToolbar: ");
        //Get the toolbar (Serialise)
        Toolbar searchToolbar = (Toolbar) findViewById(R.id.search_toolbar);
        searchToolbar.setTitle("Search Articles: Results");
        //Set the toolbar
        setSupportActionBar(searchToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        searchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getBaseContext(), "clic clic", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

}
