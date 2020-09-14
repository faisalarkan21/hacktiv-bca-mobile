package com.example.hacktiv_android_bca.service;

import com.example.hacktiv_android_bca.Entity.Agency;
import com.example.hacktiv_android_bca.Entity.Bus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BusService {
    @GET("getAllBus-angular")
    Call<ArrayList<Bus>> getBuses(@Query("id") String id);
}
