package com.vivant.annecharlotte.mynews;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vivant.annecharlotte.mynews.API.ApiKey;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIClient;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIInterface;
import com.vivant.annecharlotte.mynews.Models.Doc;
import com.vivant.annecharlotte.mynews.Models.NYTSearchArticles;
import com.vivant.annecharlotte.mynews.Views.ListOfSearchedArticlesAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultSearchFragment extends Fragment {

    private static final String BUSINESS_SEARCH = "source:(\"The New York Times\")" + " AND" + " news_desk:(\"Business\")"; // pour test

    private RelativeLayout mRelativeLayout;
    private RecyclerView mRecyclerView;

    private LinearLayout mArticleItem;
    private String articleUrl;
    private WebViewActivity mArticleWebView = new WebViewActivity();

    public static final String TAG = "resultsearchfragment";
    public static final String TAG_API = "SEARCH";

    public String getTagApi() {
        return TAG_API;
    }

    private ListOfSearchedArticlesAdapter adapter;
    private List<Doc> mListArticles;

    private String mFQuery ;
    private String mQuery;
    private String mBeginDate ;
    private String mEndDate ;

    public ResultSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_articles_page, container, false);
        mRecyclerView = view.findViewById(R.id.fragment_articles_recyclerview);
        mRelativeLayout = view.findViewById(R.id.fragment_articles_page_recyclerview);
        mArticleItem = view.findViewById(R.id.article_item);

        if (getArguments()!=null) {

            mQuery = getArguments().getString("q");
            mFQuery = getArguments().getString("fq");
            mBeginDate = getArguments().getString("begin_date");
            mEndDate = getArguments().getString("end_date");
        }

        Log.d(TAG, "onCreateView: mFQuery: " + mFQuery + ", mQuery: "+ mQuery+ ", mBeginDate: " + mBeginDate + ", mEndDate: " + mEndDate );
        this.configureRecyclerView();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void configureRecyclerView() {
        Log.d(TAG, "configureRecyclerView: entrée ");

        NYTimesAPIInterface apiService = NYTimesAPIClient.getClient().create(NYTimesAPIInterface.class);
        Call<NYTSearchArticles> call = apiService.loadSearch(ApiKey.NYT_API_KEY,mQuery, mFQuery,  getContext().getString(R.string.sort_by_newest), mBeginDate, mEndDate);

        call.enqueue(new Callback<NYTSearchArticles>() {
            @Override
            public void onResponse(Call<NYTSearchArticles> call, Response<NYTSearchArticles> response) {
                Log.d(TAG, "configureRecyclerView: onResponse ");
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                NYTSearchArticles posts = response.body();
                mListArticles = posts.getResponse().getDocs();
                if (mListArticles.isEmpty()) {

                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog);
                    } else {
                        builder = new AlertDialog.Builder(getContext());
                    }
                    builder.setTitle(R.string.searchdialog_title)
                            .setMessage(R.string.searchdialog_text)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent myIntent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(myIntent);
                                }
                            })
                            .setIcon(R.drawable.baseline_sentiment_very_dissatisfied_24)
                            .show();
                }

                adapter = new ListOfSearchedArticlesAdapter(mListArticles, Glide.with(mRecyclerView), TAG_API);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(adapter);

                adapter.setOnItemClickedListener(new ListOfSearchedArticlesAdapter.OnItemClickedListener() {
                    @Override
                    public void OnItemClicked(int position) {
                        articleUrl = mListArticles.get(position).getWebUrl();
                        Intent WVIntent = new Intent(getContext(), WebViewActivity.class);
                        WVIntent.putExtra("ArticleURL", articleUrl);
                        startActivity(WVIntent);
                    }
                });
            }

            @Override
            public void onFailure(Call<NYTSearchArticles> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

                Log.e(TAG, t.toString());
            }
        });
    }
}

