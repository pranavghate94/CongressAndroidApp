package com.example.ghate.congress;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ghate on 11/17/2016.
 */

public class BillVersion {

    @SerializedName("version_name")
    private String versionName;

    @SerializedName("urls")
    private VersionUrl urls;

    public BillVersion(String versionName, VersionUrl urls) {
        this.versionName = versionName;
        this.urls = urls;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public VersionUrl getUrls() {
        return urls;
    }

    public void setUrls(VersionUrl urls) {
        this.urls = urls;
    }
}
