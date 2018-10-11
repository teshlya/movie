package com.example.misha.movies.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MovieData implements Serializable {

    @SerializedName("title")
    String title;

    @SerializedName("poster_path")
    String thumbnail;

    @SerializedName("overview")
    String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
