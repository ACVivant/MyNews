package com.vivant.annecharlotte.mynews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vivant.annecharlotte.mynews.API.NYTimesAPIClient;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIInterface;
import com.vivant.annecharlotte.mynews.Models.ApiKey;
import com.vivant.annecharlotte.mynews.Models.NYTTopStoriesArticles;

import butterknife.BindView;
import io.reactivex.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopStoriesPageFragment extends Fragment {

   // @BindView(R.id.fragment_topstories_textview) TextView textViewTS;
    TextView textViewTS;
    public static final String TAG = "topstories_zut";

    public TopStoriesPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView ");

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_top_stories_page, container, false);
        textViewTS = view.findViewById(R.id.fragment_topstories_textview);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //this.textViewTS.setText("il est passé par ici");

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
                Toast.makeText(getContext(), "Victoire code: " + response.code(), Toast.LENGTH_LONG).show();

                String affichage="";
                for (int i = 0; i<10; i++) {
                    affichage += "Title: " + posts.getResults().get(i).getTitle() + "\n";
                    affichage += "Abstract: " + posts.getResults().get(i).getAbstract() + "\n\n";
                }
                textViewTS.append(affichage); ;
            }

            @Override
            public void onFailure(Call<NYTTopStoriesArticles> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

                Log.e(TAG, t.toString());
            }
        });
    }
}