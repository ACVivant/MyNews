package com.vivant.annecharlotte.mynews;


import android.content.Intent;
import android.os.Bundle;
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
import com.vivant.annecharlotte.mynews.API.NYTimesAPIClient;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIInterface;
import com.vivant.annecharlotte.mynews.API.ApiKey;
import com.vivant.annecharlotte.mynews.Models.NYTArticles;
import com.vivant.annecharlotte.mynews.Models.NYTTopStoriesArticles;
import com.vivant.annecharlotte.mynews.Models.ResultArticles;
import com.vivant.annecharlotte.mynews.Models.ResultTopStories;
import com.vivant.annecharlotte.mynews.Views.ListOfArticlesAdapter;

import java.util.List;

import io.reactivex.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NoTopStoriesPageFragment extends Fragment  {

    private RecyclerView mRecyclerView;
    private LinearLayout mArticleItem;
    private OnArticleClickedListener mOnArticleClickedListener;
    private String articleUrl;
    private WebViewActivity mArticleWebView = new WebViewActivity();

    public interface OnArticleClickedListener {
         void onArticletClicked(int position);
    }

    public static final String TAG = "topstories_zut";

    public static final String TAG_API = "TOPSTORIES";
    public String getTagApi() { return TAG_API;}

    private ListOfArticlesAdapter adapter;
    private List<ResultArticles> mListArticles;

    public NoTopStoriesPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView ");

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_articles_page, container, false);
        mRecyclerView = view.findViewById(R.id.fragment_articles_recyclerview);
        mArticleItem = view.findViewById(R.id.article_item);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: entrée ");

        NYTimesAPIInterface apiService = NYTimesAPIClient.getClient().create(NYTimesAPIInterface.class);
        Call<NYTArticles> call = apiService.loadTopStories(ApiKey.NYT_API_KEY);

        call.enqueue(new Callback<NYTArticles>() {
            @Override
            public void onResponse(Call<NYTArticles> call, Response<NYTArticles> response) {
                Log.d(TAG, "onCreate: onResponse ");
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                NYTArticles posts = response.body();
                mListArticles = posts.getResults();

                adapter = new ListOfArticlesAdapter(mListArticles, Glide.with(mRecyclerView), TAG_API);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(adapter);

                adapter.setOnItemClickedListener(new ListOfArticlesAdapter.OnItemClickedListener() {
                    @Override
                    public void OnItemClicked(int position) {
                        articleUrl = mListArticles.get(position).getUrl();
                        //mArticleWebView.setURL(articleUrl);
                        Intent WVIntent = new Intent(getContext(), WebViewActivity.class);
                        WVIntent.putExtra("ArticleURL", articleUrl);
                        startActivity(WVIntent);
                    }
                });
            }

            @Override
            public void onFailure(Call<NYTArticles> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

                Log.e(TAG, t.toString());
            }
        });
    }
}