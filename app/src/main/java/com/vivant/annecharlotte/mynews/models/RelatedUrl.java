package com.vivant.annecharlotte.mynews.models;

/**
 * Created by Anne-Charlotte Vivant on 17/12/2018.
 */
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class RelatedUrl {

    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
