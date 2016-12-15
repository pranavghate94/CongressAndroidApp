package com.example.ghate.congress;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ghate on 11/17/2016.
 */

public class Bill {

    @SerializedName("bill_id")
    private String billId;
    @SerializedName("short_title")
    private String shortTitle;
    @SerializedName("introduced_on")
    private String introducedOn;
    @SerializedName("bill_type")
    private String billType;
    @SerializedName("sponsor")
    private BillSponsor sponsor;
    @SerializedName("history")
    private BillHistory history;
    @SerializedName("urls")
    private BillUrl urls;
    @SerializedName("last_version")
    private BillVersion lastVersion;
    @SerializedName("official_title")
    private String officialTitle;
    @SerializedName("chamber")
    private String chamber;

    public String getOfficialTitle() {
        return officialTitle;
    }

    public void setOfficialTitle(String officialTitle) {
        this.officialTitle = officialTitle;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public Bill(String chamber, String billId, String shortTitle, String introducedOn, String billType, BillSponsor sponsor, BillHistory history, BillUrl urls, BillVersion lastVersion, String officialTitle) {
        this.billId = billId;
        this.shortTitle = shortTitle;
        this.introducedOn = introducedOn;
        this.billType = billType;
        this.sponsor = sponsor;
        this.history = history;
        this.urls = urls;
        this.lastVersion = lastVersion;
    }

    public String getBillId() {

        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getIntroducedOn() {
        return introducedOn;
    }

    public void setIntroducedOn(String introducedOn) {
        this.introducedOn = introducedOn;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public BillSponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(BillSponsor sponsor) {
        this.sponsor = sponsor;
    }

    public BillHistory getHistory() {
        return history;
    }

    public void setHistory(BillHistory history) {
        this.history = history;
    }

    public BillUrl getUrls() {
        return urls;
    }

    public void setUrls(BillUrl urls) {
        this.urls = urls;
    }

    public BillVersion getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(BillVersion lastVersion) {
        this.lastVersion = lastVersion;
    }
}
