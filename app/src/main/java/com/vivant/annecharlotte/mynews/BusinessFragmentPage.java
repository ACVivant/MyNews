package com.vivant.annecharlotte.mynews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vivant.annecharlotte.mynews.API.NYTimesAPIClient;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIInterface;
import com.vivant.annecharlotte.mynews.API.ApiKey;
import com.vivant.annecharlotte.mynews.Models.NYTSearchArticles;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BusinessFragmentPage extends Fragment{


   private TextView textView;
   private static final String BUSINESS_SEARCH = "source:(\"The New York Times\")" + " AND" + " news_desk:(\"Business\")";


    public static final String TAG = "business_zut";

   public BusinessFragmentPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business_page, container, false);
        textView = view.findViewById(R.id.fragment_business_textview);
        Log.d(TAG, "onCreateView ");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //this.textView.setText("il est passé par ici");

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
                Log.d(TAG, "onCreate: onResponse TV ");
                String affichage ="section: " +  posts.getResponse().getDocs().get(0).getSectionName() +"\nsujet: " + posts.getResponse().getDocs().get(0).getSnippet();
                textView.setText(affichage);
            }

            @Override
            public void onFailure(Call<NYTSearchArticles> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

                Log.e(TAG, t.toString());
            }
        });
    }
}
