package com.example.hacktiv_android_bca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hacktiv_android_bca.Entity.Register;
import com.example.hacktiv_android_bca.service.AuthService;
import com.example.hacktiv_android_bca.service.RegisterService;
import com.example.hacktiv_android_bca.service.UtilsApi;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edName, edPassword, edEmail, edAgency, edDetails, edContact;
    Button btnSubmit;
    RegisterService mRegisterAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edEmail = findViewById(R.id.email);
        edName = findViewById(R.id.name);
        edPassword = findViewById(R.id.password);
        edAgency = findViewById(R.id.agency_name);
        edDetails = findViewById(R.id.agencyDetail);
        edContact = findViewById(R.id.contact_number);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(this);


    }

    public void doPostData () {
        mRegisterAPIService = UtilsApi.getRegisterAPIService();

        Register register = new Register();
        register.setAgencyName(edName.getText().toString());
        register.setEmail(edEmail.getText().toString());
        register.setPassword(edPassword.getText().toString());
        register.setAgencyName(edAgency.getText().toString());
        register.setAgencyDetail(edDetails.getText().toString());
        register.setContactNumber(edContact.getText().toString());

        Call<ResponseBody> response = mRegisterAPIService.postRegister(register);

        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse) {
                if (rawResponse.isSuccessful()) {
                    try {
                        Intent goHome = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(goHome);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Password / Username Salah",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

                Toast.makeText(RegisterActivity.this, throwable.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                    doPostData();
                break;
            default:
                break;
        }
    }
}
