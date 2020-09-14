package com.example.hacktiv_android_bca.service;

import com.example.hacktiv_android_bca.Entity.Register;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterService {

    @POST("createNewAccount")
    Call<ResponseBody> postRegister(@Body Register register);

}
