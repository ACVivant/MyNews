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

import java.util.List;

import io.reactivex.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class NYTPageFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayout mArticleItem;
    private String articleUrl;
    private WebViewActivity mArticleWebView = new WebViewActivity();

    public interface OnArticleClickedListener {
        void onArticletClicked(int position);
    }

    public static final String TAG = "mostpopular_zut";
    public String TAG_API ;
    public String getTagApi() { return TAG_API;}

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
        MyDividerItemDecoration mDividerItemDecoration = new MyDividerItemDecoration(mRecyclerView.getContext());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        mArticleItem = view.findViewById(R.id.article_item);

        Log.d(TAG, "onCreateView: pos: " +indexAPI );
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            indexAPI = getArguments().getInt("pos");
        }

        Log.d(TAG, "onCreate: entrée ");
        Log.d(TAG, "onCreate: index position: " + indexAPI);

        NYTimesAPIInterface apiService = NYTimesAPIClient.getClient().create(NYTimesAPIInterface.class);

        switch (indexAPI) {
            case 0:
            TAG_API = "TOPSTORIES";
            call = apiService.loadTopStories(ApiKey.NYT_API_KEY);
            Log.d(TAG, "onCreate: passage par le if = 0");
            break;

            case 1:
            TAG_API = "MOSTPOPULAR";
            call = apiService.loadMostPopular(ApiKey.NYT_API_KEY);
            Log.d(TAG, "onCreate: passage par le if = 1");
            break;
        }

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

                //adapter = new ListOfArticlesAdapter(mListArticles);
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

    public static NYTPageFragment newInstance(int position) {
        NYTPageFragment f = new NYTPageFragment();
        Bundle bTransfert = new Bundle();

        bTransfert.putInt("pos", position);
        f.setArguments(bTransfert);
            return f;
        }

}

