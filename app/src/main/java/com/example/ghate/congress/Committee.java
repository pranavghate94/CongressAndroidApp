package com.example.ghate.congress;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ghate on 11/17/2016.
 */

public class Committee {

    @SerializedName("committee_id")
    private String committeeId;
    @SerializedName("name")
    private String name;
    @SerializedName("chamber")
    private String chamber;
    @SerializedName("subcommittee")
    private Boolean subcommittee;
    @SerializedName("parent_committee_id")
    private String parentCommitteeId;
    @SerializedName("phone")
    private String phone;
    @SerializedName("office")
    private String office;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Committee(String committeeId, String name, String chamber, Boolean subcommittee, String parentCommitteeId, String phone, String office) {
        this.committeeId = committeeId;
        this.name = name;
        this.chamber = chamber;
        this.subcommittee = subcommittee;
        this.parentCommitteeId = parentCommitteeId;
        this.phone = phone;
        this.office = office;

    }

    public String getCommitteeId() {

        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public Boolean getSubcommittee() {
        return subcommittee;
    }

    public void setSubcommittee(Boolean subcommittee) {
        this.subcommittee = subcommittee;
    }

    public String getParentCommitteeId() {
        return parentCommitteeId;
    }

    public void setParentCommitteeId(String parentCommitteeId) {
        this.parentCommitteeId = parentCommitteeId;
    }
}
