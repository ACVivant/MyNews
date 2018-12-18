package com.vivant.annecharlotte.mynews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vivant.annecharlotte.mynews.API.NYTimesAPIClient;
import com.vivant.annecharlotte.mynews.API.NYTimesAPIInterface;
import com.vivant.annecharlotte.mynews.Models.ApiKey;
import com.vivant.annecharlotte.mynews.Models.NYTMostPopularArticles;

import butterknife.BindView;
import io.reactivex.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularPageFragment extends Fragment {

    TextView textViewMP;
    public static final String TAG = "MostPopular_zut";

    public MostPopularPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView ");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_most_popular_page, container, false);
        textViewMP =view.findViewById(R.id.fragment_mostpopular_textview);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: entrée ");

        NYTimesAPIInterface apiService = NYTimesAPIClient.getClient().create(NYTimesAPIInterface.class);
        Call<NYTMostPopularArticles> call = apiService.loadMostPopular(ApiKey.NYT_API_KEY);

        call.enqueue(new Callback<NYTMostPopularArticles>() {
            @Override
            public void onResponse(Call<NYTMostPopularArticles> call, Response<NYTMostPopularArticles> response) {
                Log.d(TAG, "onCreate: onResponse ");
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                NYTMostPopularArticles posts = response.body();
                Toast.makeText(getContext(), "Victoire code: " + response.code(), Toast.LENGTH_LONG).show();
                //textViewMP.setText("Victoire code: " + response.code());


                textViewMP.setText(posts.getResults().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<NYTMostPopularArticles> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

                Log.e(TAG, t.toString());
            }
        });
    }
}

