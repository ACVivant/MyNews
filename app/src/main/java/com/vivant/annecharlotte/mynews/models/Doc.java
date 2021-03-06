package com.vivant.annecharlotte.mynews.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doc {

    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("snippet")
    @Expose
    private String snippet;
    @SerializedName("pub_date")
    @Expose
    private String pubDate;
    @SerializedName("news_desK")
    @Expose
    private String newsDesK;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("subsection_name")
    @Expose
    private String subsectionName;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("multimedia")
    @Expose
    private List<Multimedium> multimedia = null;

    public String getWebUrl() {
        return webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }


}