package com.vivant.annecharlotte.mynews.api;

import com.vivant.annecharlotte.mynews.models.NYTArticles;
import com.vivant.annecharlotte.mynews.models.NYTSearchArticles;

import retrofit2.Call;
        import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Anne-Charlotte Vivant on 17/12/2018.
 */
public interface NYTimesAPIInterface {

    //Top Stories
    @GET("svc/topstories/v2/{id}")
    Call<NYTArticles> loadTopStoriesAll(@Path("id") String group, @Query("api-key") String apiKey);

    // Most Popular
    @GET("svc/mostpopular/v2/viewed/7.json")
    Call<NYTArticles> loadMostPopular(@Query("api-key") String apiKey);

    // Search API
    @GET("svc/search/v2/articlesearch.json")
    Call<NYTSearchArticles> loadSearch(@Query("api-key") String apiKey,
                                       @Query("q") String query,
                                       @Query("fq") String fQuery,
                                       @Query("sort") String sort,
                                       @Query("begin_date") String beginDate,
                                       @Query("end_date") String endDate);
}
