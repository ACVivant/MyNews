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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vivant.annecharlotte.mynews.API.ApiKey;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIClient;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIInterface;
import com.vivant.annecharlotte.mynews.Models.Doc;
import com.vivant.annecharlotte.mynews.Models.NYTSearchArticles;
import com.vivant.annecharlotte.mynews.Utils.MyDividerItemDecoration;
import com.vivant.annecharlotte.mynews.Views.ListOfSearchedArticlesAdapter;
import com.vivant.annecharlotte.mynews.Views.Popup;
import com.vivant.annecharlotte.mynews.Views.WebViewActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Generate call for search demand
 */
public class NYTSearchPageFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private String articleUrl;

    public static final String TAG_API = "SEARCH";

    private ListOfSearchedArticlesAdapter adapter;
    private List<Doc> mListArticles;

    private String mFQuery ;
    private String mQuery;
    private String mBeginDate ;
    private String mEndDate ;

    private int index;

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

            if (getArguments() != null) {
                index = getArguments().getInt("pos", 3);
                Log.d("NYTSearchPageFragment", "onCreateView: " + index);
                switch (index) {
                    case 2:
                        mQuery = "";
                        mFQuery = "source:(\"The New York Times\")" + " AND" + " news_desk:(\"Arts\")";
                        mBeginDate = "";
                        mEndDate = "";
                        break;
                    case 3:
                        mQuery = getArguments().getString("q");
                        mFQuery = getArguments().getString("fq");
                        mBeginDate = getArguments().getString("begin_date");
                        mEndDate = getArguments().getString("end_date");
                        break;
                }
            }

        NYTimesAPIInterface apiService = NYTimesAPIClient.getClient().create(NYTimesAPIInterface.class);
            Log.d("Avant call resultat", "onCreate: index " + index);
        switch (index) {
            case 2:
                Log.d("Resultat", "onCreate: index 2 " + index);
                call = apiService.loadBusiness(ApiKey.NYT_API_KEY, mFQuery, getContext().getString(R.string.sort_by_newest));
                break;
            case 3:
                Log.d("Resultat", "onCreate: index 3 " + index);
                call = apiService.loadSearch(ApiKey.NYT_API_KEY, mQuery, mFQuery, getContext().getString(R.string.sort_by_newest), mBeginDate, mEndDate);
                break;
        }

        call.enqueue(new Callback<NYTSearchArticles>() {
            @Override
            public void onResponse(Call<NYTSearchArticles> call, Response<NYTSearchArticles> response) {
                    if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                NYTSearchArticles posts = response.body();
                mListArticles = posts.getResponse().getDocs();
                if (mListArticles.isEmpty()) {

                    Popup mPopup = new Popup(getContext(), R.string.searchdialog_title, R.string.searchdialog_text, R.drawable.baseline_sentiment_very_dissatisfied_24);
                    mPopup.personnaliseAndLaunchPopup();
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

                Log.e(TAG_API, t.toString());
            }
        });
    }

    // save the position on the main tab to know which API should be called
    public static NYTSearchPageFragment newInstance(int position) {
        NYTSearchPageFragment f = new NYTSearchPageFragment();
        Bundle bTransfert = new Bundle();

        bTransfert.putInt("pos", position);
        f.setArguments(bTransfert);
        return f;
    }
}

