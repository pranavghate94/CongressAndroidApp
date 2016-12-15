package com.example.ghate.congress;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ghate on 11/17/2016.
 */

public class LegislatorResults {

    @SerializedName("results")
    private List<Legislator> results;

    public LegislatorResults(List<Legislator> results) {
        this.results = results;
    }

    public List<Legislator> getResults() {
        return results;
    }

    public void setResults(List<Legislator> results) {
        this.results = results;
    }
}
