package com.vivant.annecharlotte.mynews;

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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIClient;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIInterface;
import com.vivant.annecharlotte.mynews.API.ApiKey;
import com.vivant.annecharlotte.mynews.Models.Doc;
import com.vivant.annecharlotte.mynews.Models.NYTArticles;
import com.vivant.annecharlotte.mynews.Models.NYTSearchArticles;
import com.vivant.annecharlotte.mynews.Models.ResultArticles;
import com.vivant.annecharlotte.mynews.Utils.MyDividerItemDecoration;
import com.vivant.annecharlotte.mynews.Views.ListOfArticlesAdapter;
import com.vivant.annecharlotte.mynews.Views.ListOfSearchedArticlesAdapter;
import com.vivant.annecharlotte.mynews.Views.ListOfSearchedArticlesViewHolder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BusinessFragmentPage extends Fragment{

    private RecyclerView mRecyclerView;
    private LinearLayout mArticleItem;
    private NYTPageFragment.OnArticleClickedListener mOnArticleClickedListener;
    private String articleUrl;
    private WebViewActivity mArticleWebView = new WebViewActivity();

   private static final String BUSINESS_SEARCH = "source:(\"The New York Times\")" + " AND" + " news_desk:(\"Business\")";
   public static final String TAG = "business_zut";
    public static final String TAG_API = "BUSINESS";
    public String getTagApi() { return TAG_API;}

    private ListOfSearchedArticlesAdapter adapter;
    private List<Doc> mListArticles;

    public interface OnArticleClickedListener {
        void onArticletClicked(int position);
    }

   public BusinessFragmentPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView ");

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_articles_page, container, false);

        mRecyclerView = view.findViewById(R.id.fragment_articles_recyclerview);
        MyDividerItemDecoration mDividerItemDecoration = new MyDividerItemDecoration(mRecyclerView.getContext());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        mArticleItem = view.findViewById(R.id.article_item);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: entrée ");

        NYTimesAPIInterface apiService = NYTimesAPIClient.getClient().create(NYTimesAPIInterface.class);
        Call<NYTSearchArticles> call = apiService.loadBusiness(ApiKey.NYT_API_KEY, BUSINESS_SEARCH, getContext().getString(R.string.sort_by_newest));

        call.enqueue(new Callback<NYTSearchArticles>() {
            @Override
            public void onResponse(Call<NYTSearchArticles> call, Response<NYTSearchArticles> response) {
                Log.d(TAG, "onCreate: onResponse ");
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                NYTSearchArticles posts = response.body();
                mListArticles = posts.getResponse().getDocs();

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
