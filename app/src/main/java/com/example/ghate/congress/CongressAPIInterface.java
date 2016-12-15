package com.example.ghate.congress;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ghate on 11/17/2016.
 */

public interface CongressAPIInterface {
    @GET("?operation=legislators&per_page=all")
    Call<LegislatorResults> getLegislatorDetails(@Query("chamber") String chamber);

    @GET("?operation=bills&per_page=50")
    Call<BillResults> getBillDetails(@Query("active") String active);

    @GET("?operation=committees&per_page=all")
    Call<CommitteeResults> getCommitteeDetails(@Query("chamber") String chamber);
}
