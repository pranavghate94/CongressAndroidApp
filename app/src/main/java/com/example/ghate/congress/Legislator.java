package com.example.ghate.congress;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ghate on 11/17/2016.
 */

public class Legislator {



    @SerializedName("bioguide_id")
    private String bioguideId;
    @SerializedName("party")
    private String party;
    @SerializedName("state_name")
    private String stateName;
    @SerializedName("district")
    private String district;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("title")
    private String title;
    @SerializedName("phone")
    private String phone;
    @SerializedName("oc_email")
    private String email;
    @SerializedName("term_start")
    private String termStart;
    @SerializedName("term_end")
    private String termEnd;
    @SerializedName("office")
    private String office;
    @SerializedName("facebook_id")
    private String facebook;
    @SerializedName("twitter_id")
    private String twitter;
    @SerializedName("website")
    private String website;

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Legislator(String chamber, String birthday, String fax, String website, String twitter, String facebook, String office, String termEnd, String termStart, String email, String phone, String title, String lastName, String firstName, String district, String stateName, String party, String bioguideId) {
        this.chamber = chamber;
        this.birthday = birthday;
        this.fax = fax;
        this.website = website;
        this.twitter = twitter;
        this.facebook = facebook;
        this.office = office;
        this.termEnd = termEnd;
        this.termStart = termStart;
        this.email = email;
        this.phone = phone;
        this.title = title;
        this.lastName = lastName;
        this.firstName = firstName;
        this.district = district;
        this.stateName = stateName;
        this.party = party;
        this.bioguideId = bioguideId;
    }

    public String getChamber() {
        return chamber;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setChamber(String chamber) {

        this.chamber = chamber;
    }

    @SerializedName("fax")
    private String fax;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("chamber")
    private String chamber;


    public String getBioguideId() {
        return bioguideId;
    }

    public void setBioguideId(String bioguideId) {
        this.bioguideId = bioguideId;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTermStart() {
        return termStart;
    }

    public void setTermStart(String termStart) {
        this.termStart = termStart;
    }

    public String getTermEnd() {
        return termEnd;
    }

    public void setTermEnd(String termEnd) {
        this.termEnd = termEnd;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
