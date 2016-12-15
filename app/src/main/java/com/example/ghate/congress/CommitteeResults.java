package com.example.ghate.congress;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ghate on 11/17/2016.
 */

public class CommitteeResults {

    @SerializedName("results")
    private List<Committee> results;

    public CommitteeResults(List<Committee> results) {
        this.results = results;
    }

    public List<Committee> getResults() {
        return results;
    }

    public void setResults(List<Committee> results) {
        this.results = results;
    }
}
