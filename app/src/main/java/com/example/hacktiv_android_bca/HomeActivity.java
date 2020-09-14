package com.example.hacktiv_android_bca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hacktiv_android_bca.Entity.Agency;
import com.example.hacktiv_android_bca.service.AgencyService;
import com.example.hacktiv_android_bca.service.UtilsApi;
import com.example.hacktiv_android_bca.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    AgencyService agencyService;
    SessionManager session;
    TextView txtAgencyName, txtAgencyDetails;
    Button btnListBus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        session = new SessionManager(this);

        txtAgencyName = findViewById(R.id.txt_agency_name);
        txtAgencyDetails = findViewById(R.id.txt_agency_details);
        btnListBus = findViewById(R.id.btn_list_bus);

        btnListBus.setOnClickListener(this);
    }

    public void doFetchAgency() {
        agencyService = UtilsApi.getAgencyAPIService();

        Log.d("agency", session.getByKey("agencyId"));
        Call<Agency> response = agencyService.getAgency(session.getByKey("agencyId"));

        response.enqueue(new Callback<Agency>() {
            @Override
            public void onResponse(Call<Agency> call, retrofit2.Response<Agency> rawResponse) {
                if (rawResponse.isSuccessful()) {
                    try {

                        Agency agency = rawResponse.body();
                        txtAgencyName.setText(agency.getName());
                        txtAgencyDetails.setText(agency.getDetails());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(HomeActivity.this, "Gagal Mengambil Data",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Agency> call, Throwable throwable) {
                Toast.makeText(HomeActivity.this, throwable.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        doFetchAgency();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_list_bus:
                Intent goRegister = new Intent(HomeActivity.this, ListBusActivity.class);
                startActivity(goRegister);
                break;
            default:
                break;
        }
    }


}
