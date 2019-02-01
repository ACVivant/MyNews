package com.vivant.annecharlotte.mynews.models;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import java.util.List;

public class Media {

    @SerializedName("media-metadata")
    @Expose
    private List<MediaPhoto> mediametadata;


    public List<MediaPhoto> getMediametadata() {
        return mediametadata;
    }

    public void setMediametadata(List<MediaPhoto> mediametadata) {
        this.mediametadata = mediametadata;
    }

}
