package com.vivant.annecharlotte.mynews.Models;

import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class  NYTTopStoriesArticles {

    @SerializedName("results")
    @Expose
    private List<ResultTopStories> results = null;

    public List<ResultTopStories> getResults() {
        return results;
    }

    public void setResults(List<ResultTopStories> results) {
        this.results = results;
    }

}