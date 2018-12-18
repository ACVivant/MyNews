package com.vivant.annecharlotte.mynews.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Anne-Charlotte Vivant on 17/12/2018.
 */
public class NYTimesAPIClient {

    private static final String BASE_URL = "https://api.nytimes.com/";

    public static Retrofit getClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
