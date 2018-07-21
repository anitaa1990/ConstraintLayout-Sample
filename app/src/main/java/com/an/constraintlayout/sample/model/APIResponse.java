package com.an.constraintlayout.sample.model;

import java.io.Serializable;
import java.util.List;

public class APIResponse implements Serializable {

    private long id;
    private List<Video> results;
    private List<Cast> cast;
    private List<Crew> crew;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }
}
