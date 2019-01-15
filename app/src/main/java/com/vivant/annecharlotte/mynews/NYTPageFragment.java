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
import com.vivant.annecharlotte.mynews.Models.ResultArticles;
import com.vivant.annecharlotte.mynews.Utils.MyDividerItemDecoration;
import com.vivant.annecharlotte.mynews.Views.ListOfArticlesAdapter;
import com.vivant.annecharlotte.mynews.Views.WebViewActivity;

import java.util.List;

import io.reactivex.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Generate Top Stories and Most Popular pages
 */
public class NYTPageFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayout mArticleItem;
    private String articleUrl;

    public String TAG_API ;

    private ListOfArticlesAdapter adapter;
    private List<ResultArticles> mListArticles;

    private int indexAPI;
    private  Call<NYTArticles> call;

    public NYTPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_articles_page, container, false);
        mRecyclerView = view.findViewById(R.id.fragment_articles_recyclerview);
        mArticleItem = view.findViewById(R.id.article_item);

        // Add horizontal separators
        MyDividerItemDecoration mDividerItemDecoration = new MyDividerItemDecoration(mRecyclerView.getContext());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Call to the New-York Times API

        // read the position on the main tab to know which API should be called
        if (getArguments() != null) {
            indexAPI = getArguments().getInt("pos");
        }

        NYTimesAPIInterface apiService = NYTimesAPIClient.getClient().create(NYTimesAPIInterface.class);

        switch (indexAPI) {
            case 0:
            TAG_API = "TOPSTORIES";
            call = apiService.loadTopStories(ApiKey.NYT_API_KEY);
            break;

            case 1:
            TAG_API = "MOSTPOPULAR";
            call = apiService.loadMostPopular(ApiKey.NYT_API_KEY);
            break;
        }

        call.enqueue(new Callback<NYTArticles>() {
            @Override
            public void onResponse(Call<NYTArticles> call, Response<NYTArticles> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                // fill the recyclerview
                NYTArticles posts = response.body();
                mListArticles = posts.getResults();

                adapter = new ListOfArticlesAdapter(mListArticles, Glide.with(mRecyclerView), TAG_API);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(adapter);

                // Launch WebViewActiviy when user clicks on an articles item
                adapter.setOnItemClickedListener(new ListOfArticlesAdapter.OnItemClickedListener() {
                    @Override
                    public void OnItemClicked(int position) {
                        articleUrl = mListArticles.get(position).getUrl();
                        Intent WVIntent = new Intent(getContext(), WebViewActivity.class);
                        WVIntent.putExtra("ArticleURL", articleUrl);
                        startActivity(WVIntent);
                    }
                });
            }

            @Override
            public void onFailure(Call<NYTArticles> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG_API, t.toString());
            }
        });
    }

    // save the position on the main tab to know which API should be called
    public static NYTPageFragment newInstance(int position) {
        NYTPageFragment f = new NYTPageFragment();
        Bundle bTransfert = new Bundle();

        bTransfert.putInt("pos", position);
        f.setArguments(bTransfert);
            return f;
        }

}

