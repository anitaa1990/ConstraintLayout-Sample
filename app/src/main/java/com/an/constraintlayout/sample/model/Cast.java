package com.an.constraintlayout.sample.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cast implements Serializable {

    private transient String IMAGE_URL = "https://image.tmdb.org/t/p/w500%s";

    @SerializedName("cast_id")
    private long castId;

    private String character;

    @SerializedName("credit_id")
    private String creditId;

    private long id;
    private String name;
    private int order;

    @SerializedName("profile_path")
    private String profilePath;

    public long getCastId() {
        return castId;
    }

    public void setCastId(long castId) {
        this.castId = castId;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getProfilePath() {
        if(profilePath != null && !profilePath.startsWith("http")) {
            profilePath = String.format(IMAGE_URL, profilePath);
        }
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
