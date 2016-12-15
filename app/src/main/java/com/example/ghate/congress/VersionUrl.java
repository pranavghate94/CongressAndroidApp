package com.example.ghate.congress;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ghate on 11/17/2016.
 */

public class VersionUrl {
    @SerializedName("pdf")
    public String pdf;

    public VersionUrl(String pdf) {
        this.pdf = pdf;
    }

    public String getPdf() {

        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
