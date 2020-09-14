package com.example.hacktiv_android_bca.service;

import com.example.hacktiv_android_bca.Entity.Agency;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthService {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> postLogin(@Field("email") String email,
                                 @Field("password") String password);

}
