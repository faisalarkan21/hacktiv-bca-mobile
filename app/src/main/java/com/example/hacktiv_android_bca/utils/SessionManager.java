package com.example.hacktiv_android_bca.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;

import java.util.Map;

public class SessionManager {

    private SharedPreferences prefs;

    public SessionManager(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setSession(String token) {
        JWT jwt = new JWT(token);
        Map<String, Claim> getClaims = jwt.getClaims();
        String name = getClaims.get("name").asString();
        String agencyId = getClaims.get("agencyId").asString();
        String userId = getClaims.get("userId").asString();
        String email = getClaims.get("email").asString();

        prefs.edit().putString("name", name).apply();
        prefs.edit().putString("agencyId", agencyId).apply();
        prefs.edit().putString("userId", userId).apply();
        prefs.edit().putString("email", email).apply();
    }

    public String getByKey(String key) {
        String token = prefs.getString(key, "");
        return token;
    }

    public void removeToken() {
        prefs.edit().remove("token").apply();
    }
}



