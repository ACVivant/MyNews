package com.vivant.annecharlotte.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  NYTSearchArticles {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
