package com.vivant.annecharlotte.mynews.API;

import com.vivant.annecharlotte.mynews.Models.NYTMostPopularArticles;
import com.vivant.annecharlotte.mynews.Models.NYTSearchArticles;
import com.vivant.annecharlotte.mynews.Models.NYTTopStoriesArticles;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Anne-Charlotte Vivant on 17/12/2018.
 */
public interface NYTimesAPIInterface {

    // Most Popular
    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json")
    Call<NYTMostPopularArticles> loadMostPopular(@Query("api-key") String apiKey);

    // Top Stories
    @GET("svc/topstories/v2/home.json")
    Call<NYTTopStoriesArticles> loadTopStories(@Query("api-key") String apiKey);


    // Business Tab (Search API on Business)
    @GET("svc/search/v2/articlesearch.json")
    Call<NYTSearchArticles> loadBusiness(@Query("api-key") String apiKey, @Query("fq") String fQuery, @Query("sort") String sort);
}
