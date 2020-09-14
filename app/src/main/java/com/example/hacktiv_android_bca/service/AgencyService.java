package com.example.hacktiv_android_bca.service;

import com.example.hacktiv_android_bca.Entity.Agency;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AgencyService {

    @GET("getAgency")
    Call<Agency> getAgency(@Query("id") String id);
}
