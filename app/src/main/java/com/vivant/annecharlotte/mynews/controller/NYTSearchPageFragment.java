package com.vivant.annecharlotte.mynews.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vivant.annecharlotte.mynews.api.ApiKey;
import com.vivant.annecharlotte.mynews.api.NYTimesAPIClient;
import com.vivant.annecharlotte.mynews.api.NYTimesAPIInterface;
import com.vivant.annecharlotte.mynews.models.Doc;
import com.vivant.annecharlotte.mynews.models.NYTSearchArticles;
import com.vivant.annecharlotte.mynews.R;
import com.vivant.annecharlotte.mynews.utils.MyDividerItemDecoration;
import com.vivant.annecharlotte.mynews.views.ListOfSearchedArticlesAdapter;
import com.vivant.annecharlotte.mynews.views.Popup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Generate call for search API
 */
public class NYTSearchPageFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private String articleUrl;

    public static final String TAG_API = "SEARCH";
    public static final String KEYWORD_QUERY = "q";
    public static final String NEWSDESK_QUERY = "fq";
    public static final String BEGIN_DATE = "begin_date";
    public static final String END_DATE = "end_date";
    public static final String URL_ARTICLE = "articleURL";

    private ListOfSearchedArticlesAdapter adapter;
    private List<Doc> mListArticles;

    private String mFQuery ;
    private String mQuery;
    private String mBeginDate ;
    private String mEndDate ;

    private  Call<NYTSearchArticles> call;

    public NYTSearchPageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_articles_page, container, false);
        mRecyclerView = view.findViewById(R.id.fragment_articles_recyclerview);
        MyDividerItemDecoration mDividerItemDecoration = new MyDividerItemDecoration(mRecyclerView.getContext());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load what user wants to search
        if (getArguments() != null) {
            mQuery = getArguments().getString(KEYWORD_QUERY);
            mFQuery = getArguments().getString(NEWSDESK_QUERY);
            mBeginDate = getArguments().getString(BEGIN_DATE);
            mEndDate = getArguments().getString(END_DATE);
        }

        // make the request
        NYTimesAPIInterface apiService = NYTimesAPIClient.getClient().create(NYTimesAPIInterface.class);
        call = apiService.loadSearch(ApiKey.NYT_API_KEY, mQuery, mFQuery, getContext().getString(R.string.sort_by_newest), mBeginDate, mEndDate);

        call.enqueue(new Callback<NYTSearchArticles>() {
            @Override
            public void onResponse(Call<NYTSearchArticles> call, Response<NYTSearchArticles> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                NYTSearchArticles posts = response.body();
                mListArticles = posts.getResponse().getDocs();

                // show a popup when there is no article to show
                if (mListArticles.isEmpty()) {
                    Popup mPopup = new Popup(getContext(), R.string.searchdialog_title, R.string.searchdialog_text, R.drawable.baseline_sentiment_very_dissatisfied_24);
                    mPopup.personnaliseAndLaunchPopup();

                }

                else {
                    //fill the view
                    adapter = new ListOfSearchedArticlesAdapter(mListArticles, Glide.with(mRecyclerView), TAG_API);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerView.setAdapter(adapter);

                    // makes the link with URL for WebView
                    adapter.setOnItemClickedListener(new ListOfSearchedArticlesAdapter.OnItemClickedListener() {
                        @Override
                        public void OnItemClicked(int position) {
                            articleUrl = mListArticles.get(position).getWebUrl();
                            Intent WVIntent = new Intent(getContext(), WebViewActivity.class);
                            WVIntent.putExtra(URL_ARTICLE, articleUrl);
                            startActivity(WVIntent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<NYTSearchArticles> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG_API, t.toString());
            }
        });
    }
}

