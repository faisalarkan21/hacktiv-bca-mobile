package com.example.hacktiv_android_bca.service;

import com.example.hacktiv_android_bca.utils.ApiClient;

public class UtilsApi {

    public static AuthService getAuthAPIService() {
        return ApiClient.getClient().create(AuthService.class);
    }


    public static AgencyService getAgencyAPIService() {
        return ApiClient.getClient().create(AgencyService.class);
    }


    public static BusService getBusAPIService() {
        return ApiClient.getClient().create(BusService.class);
    }

    public static RegisterService getRegisterAPIService() {
        return ApiClient.getClient().create(RegisterService.class);
    }

}
