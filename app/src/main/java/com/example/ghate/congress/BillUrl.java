package com.example.ghate.congress;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ghate on 11/17/2016.
 */

public class BillUrl {

    @SerializedName("congress")
    private String congress;

    public BillUrl(String congress) {
        this.congress = congress;
    }

    public String getCongress() {

        return congress;
    }

    public void setCongress(String congress) {
        this.congress = congress;
    }
}
