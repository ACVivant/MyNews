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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIClient;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIInterface;
import com.vivant.annecharlotte.mynews.Models.ApiKey;
import com.vivant.annecharlotte.mynews.Models.NYTTopStoriesArticles;
import com.vivant.annecharlotte.mynews.Models.ResultTopStories;
import com.vivant.annecharlotte.mynews.Views.ListOfArticlesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopStoriesPageFragment extends Fragment  {

    private RecyclerView mRecyclerView;
    private LinearLayout mArticleItem;
    private OnArticleClickedListener mOnArticleClickedListener;
    private String articleUrl;
    private WebViewActivity mArticleWebView = new WebViewActivity();

    public interface OnArticleClickedListener {
         void onArticletClicked(int position);
    }

    public static final String TAG = "topstories_zut";

    private ListOfArticlesAdapter adapter;
    private List<ResultTopStories> mListArticles;

    public TopStoriesPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView ");

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_top_stories_page, container, false);
        mRecyclerView = view.findViewById(R.id.fragment_topstories_recyclerview);
        mArticleItem = view.findViewById(R.id.article_item);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: entrée ");

        NYTimesAPIInterface apiService = NYTimesAPIClient.getClient().create(NYTimesAPIInterface.class);
        Call<NYTTopStoriesArticles> call = apiService.loadTopStories(ApiKey.NYT_API_KEY);

        call.enqueue(new Callback<NYTTopStoriesArticles>() {
            @Override
            public void onResponse(Call<NYTTopStoriesArticles> call, Response<NYTTopStoriesArticles> response) {
                Log.d(TAG, "onCreate: onResponse ");
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                NYTTopStoriesArticles posts = response.body();
                mListArticles = posts.getResults();

                //adapter = new ListOfArticlesAdapter(mListArticles);
                adapter = new ListOfArticlesAdapter(mListArticles, Glide.with(mRecyclerView));
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
            public void onFailure(Call<NYTTopStoriesArticles> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

                Log.e(TAG, t.toString());
            }
        });
    }
}