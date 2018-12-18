package com.vivant.annecharlotte.mynews.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Anne-Charlotte Vivant on 17/12/2018.
 */
public interface NYTimesAPI {

    @GET("svc/search/v2/articlesearch.json")
    Call<NYTimesAPI> getResponse();
}
