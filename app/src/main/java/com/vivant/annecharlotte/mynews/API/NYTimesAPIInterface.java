package com.vivant.annecharlotte.mynews.API;

import com.vivant.annecharlotte.mynews.Models.NYTArticles;
import com.vivant.annecharlotte.mynews.Models.NYTSearchArticles;

import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Query;

/**
 * Created by Anne-Charlotte Vivant on 17/12/2018.
 */
public interface NYTimesAPIInterface {

    // Most Popular
    //@GET("svc/mostpopular/v2/mostviewed/all-sections/7.json")
    @GET("svc/mostpopular/v2/viewed/7.json")
    Call<NYTArticles> loadMostPopular(@Query("api-key") String apiKey);

    // Top Stories
    @GET("svc/topstories/v2/home.json")
    Call<NYTArticles> loadTopStories(@Query("api-key") String apiKey);

    // Top Stories Arts
    @GET("svc/topstories/v2/arts.json")
    Call<NYTArticles> loadArts(@Query("api-key") String apiKey);

    // Top Stories Food
    @GET("svc/topstories/v2/food.json")
    Call<NYTArticles> loadFood(@Query("api-key") String apiKey);

    // Top Stories Health
    @GET("svc/topstories/v2/health.json")
    Call<NYTArticles> loadHealth(@Query("api-key") String apiKey);

    // Top Stories Science
    @GET("svc/topstories/v2/science.json")
    Call<NYTArticles> loadScience(@Query("api-key") String apiKey);

    // Top Stories Technology
    @GET("svc/topstories/v2/technology.json")
    Call<NYTArticles> loadTechnology(@Query("api-key") String apiKey);

    // Search API
    @GET("svc/search/v2/articlesearch.json")
    Call<NYTSearchArticles> loadSearch(@Query("api-key") String apiKey,
                                       @Query("q") String query,
                                       @Query("fq") String fQuery,
                                       @Query("sort") String sort,
                                       @Query("begin_date") String beginDate,
                                       @Query("end_date") String endDate);
}
