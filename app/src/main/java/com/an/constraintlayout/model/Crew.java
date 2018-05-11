package com.an.constraintlayout.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Crew implements Serializable {

    @SerializedName("credit_id")
    private String creditId;

    private long id;
    private String name;

    @SerializedName("profile_path")
    private String profilePath;

    private String department;
    private String job;

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

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
