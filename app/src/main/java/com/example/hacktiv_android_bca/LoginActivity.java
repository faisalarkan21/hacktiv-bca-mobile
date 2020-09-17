package com.example.hacktiv_android_bca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hacktiv_android_bca.service.AuthService;
import com.example.hacktiv_android_bca.service.UtilsApi;
import com.example.hacktiv_android_bca.utils.SessionManager;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edEmail, edPassword;
    Button btnLogin, btnRegist;
    AuthService mAuthAPIService;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btn_login);
        btnRegist = findViewById(R.id.btn_register);

        edEmail = findViewById(R.id.ed_email);
        edPassword = findViewById(R.id.ed_password);

        session = new SessionManager(this);

        btnLogin.setOnClickListener(this);
        btnRegist.setOnClickListener(this);

    }

    public void doLogin(){
        mAuthAPIService = UtilsApi.getAuthAPIService();

        Call<ResponseBody> response = mAuthAPIService.postLogin(edEmail.getText().toString(),  edPassword.getText().toString());

        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse) {
                if (rawResponse.isSuccessful()) {
                    try {

                        JSONObject jsonObject = new JSONObject(rawResponse.body().string());
                        String jsonToken = jsonObject.getString("data");
                        session.setSession(jsonToken);
                        Intent goHome = new Intent(LoginActivity.this, HomeTabActivity.class);
                        startActivity(goHome);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Password / Username Salah",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

                Toast.makeText(LoginActivity.this, throwable.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                Intent goRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(goRegister);
                break;
            case R.id.btn_login:
                doLogin();
                break;
            default:
                break;
        }
    }
}
