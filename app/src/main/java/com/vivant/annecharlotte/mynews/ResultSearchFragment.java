package com.vivant.annecharlotte.mynews;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.vivant.annecharlotte.mynews.Views.ListOfArticlesAdapter;
import com.vivant.annecharlotte.mynews.Views.ListOfSearchedArticlesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    private TopStoriesPageFragment.OnArticleClickedListener mOnArticleClickedListener;
    private String articleUrl;
    private WebViewActivity mArticleWebView = new WebViewActivity();

    public static final String TAG = "searchfragment_zut";
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

    public interface OnArticleClickedListener {
        void onArticletClicked(int position);
    }

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
        Log.d(TAG, "onCreateView: ");

        if (getArguments()!=null) {

            mQuery = getArguments().getString("q");
            mFQuery = getArguments().getString("fq");
            mBeginDate = getArguments().getString("begin_date");
            mEndDate = getArguments().getString("end_date");
        }

        Log.d(TAG, "onCreateView: mFQuery: " + mFQuery + ", mQuery: "+ mQuery+ ", mBeginDate: " + mBeginDate + ", mEndDate: " + mEndDate );
        Log.d(TAG, "onCreateView: BUSINESS: " +BUSINESS_SEARCH);
        this.configureRecyclerView();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void configureRecyclerView() {
        Log.d(TAG, "configureRecyclerView: entrée ");

        NYTimesAPIInterface apiService = NYTimesAPIClient.getClient().create(NYTimesAPIInterface.class);
        //Call<NYTSearchArticles> call = apiService.loadSearch(ApiKey.NYT_API_KEY, ("love children"), "news_desk: (\"arts\" \"entrepreneurs\")",  getContext().getString(R.string.sort_by_newest), "20181001", "20181220");
        Call<NYTSearchArticles> call = apiService.loadSearch(ApiKey.NYT_API_KEY,mQuery, mFQuery,  getContext().getString(R.string.sort_by_newest), mBeginDate, mEndDate);
        //Call<NYTSearchArticles> call = apiService.loadSearch(ApiKey.NYT_API_KEY, mQuery, mFQuery,  getContext().getString(R.string.sort_by_newest), mBeginDate, mEndDate);

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

                adapter = new ListOfSearchedArticlesAdapter(mListArticles, Glide.with(mRecyclerView), TAG_API);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(adapter);

                Log.d(TAG, "onResponse: adapter");
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

