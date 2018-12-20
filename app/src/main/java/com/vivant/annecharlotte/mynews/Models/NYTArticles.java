package com.vivant.annecharlotte.mynews.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NYTArticles {

    @SerializedName("results")
    @Expose
    private List<ResultArticles> results = null;

    public List<ResultArticles> getResults() {
        return results;
    }

    public void setResults(List<ResultArticles> results) {
        this.results = results;
    }

}