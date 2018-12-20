package com.vivant.annecharlotte.mynews;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vivant.annecharlotte.mynews.Views.ListOfArticlesAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultSearchFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private LinearLayout mArticleItem;
    private TopStoriesPageFragment.OnArticleClickedListener mOnArticleClickedListener;
    private String articleUrl;
    private WebViewActivity mArticleWebView = new WebViewActivity();

    public interface OnArticleClickedListener {
        void onArticletClicked(int position);
    }

    public static final String TAG = "resultsearch_zut";

    private ListOfArticlesAdapter adapter;
    private List<ResultSearchFragment> mListArticles;

    public ResultSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_result_search, container, false);
        mRecyclerView = view.findViewById(R.id.fragment_resultsearch_recyclerview);
        mArticleItem = view.findViewById(R.id.article_item);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* un peu idiot d'ajuster tout ça sans avoir créé des classes cohérentes pour limiter la dupliccation avant

        Log.d(TAG, "onCreate: entrée ");

        String mFQuery = getArguments().getString("q");
        String mQuery = getArguments().getString("fq");
        String mBeginDate = getArguments().getString("begin_date");
        String mEndDate = getArguments().getString("end_date");

        NYTimesAPIInterface apiService = NYTimesAPIClient.getClient().create(NYTimesAPIInterface.class);
        Call<NYTSearchArticles> call = apiService.loadSearch(ApiKey.NYT_API_KEY, mFQuery,mQuery, getContext().getString(R.string.sort_by_newest), mBeginDate, mEndDate);

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

                adapter = new ListOfArticlesAdapter(mListArticles, Glide.with(mRecyclerView));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(adapter);

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
            public void onFailure(Call<NYTSearchArticles> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

                Log.e(TAG, t.toString());
            }
        });*/
    }
}