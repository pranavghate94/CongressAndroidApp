package com.example.ghate.congress;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ghate on 11/17/2016.
 */

public class BillResults {
    @SerializedName("results")
    private List<Bill> results;

    public BillResults(List<Bill> results) {
        this.results = results;
    }

    public List<Bill> getResults() {
        return results;
    }

    public void setResults(List<Bill> results) {
        this.results = results;
    }
}
