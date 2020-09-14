package com.example.hacktiv_android_bca;

import android.os.Bundle;

import com.example.hacktiv_android_bca.Adapter.BusAdapter;
import com.example.hacktiv_android_bca.Entity.Agency;
import com.example.hacktiv_android_bca.Entity.Bus;
import com.example.hacktiv_android_bca.service.AgencyService;
import com.example.hacktiv_android_bca.service.BusService;
import com.example.hacktiv_android_bca.service.UtilsApi;
import com.example.hacktiv_android_bca.utils.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ListBusActivity extends AppCompatActivity {
    private List<Bus> busList = new ArrayList<>();
    private BusAdapter uAdapter;
    RecyclerView recyclerView;
    SessionManager session;
    BusService busService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bus);
        session = new SessionManager(this);

        uAdapter = new BusAdapter(busList, this);
        recyclerView = findViewById(R.id.bus_recycler_view);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(uAdapter);

    }


    public void doFetchBuses() {
        busService = UtilsApi.getBusAPIService();
        Call<ArrayList<Bus>> response = busService.getBuses(session.getByKey("agencyId"));

        response.enqueue(new Callback<ArrayList<Bus>>() {
            @Override
            public void onResponse(Call<ArrayList<Bus>> call, retrofit2.Response<ArrayList<Bus>> rawResponse) {
                if (rawResponse.isSuccessful()) {
                    try {
                        ArrayList<Bus> jsonArray = rawResponse.body();

                        if (jsonArray.size() == 0) {
                            Toast.makeText(ListBusActivity.this, "Tidak ada data.",
                                    Toast.LENGTH_LONG).show();
                        }

                        for (int i = 0; i < jsonArray.size(); i++) {
                            Bus bus = new Bus();
                            bus.setCode(jsonArray.get(i).getCode());
                            bus.setCapacity(jsonArray.get(i).getCapacity());
                            bus.setMake(jsonArray.get(i).getMake());
                            bus.setCreatedDate(jsonArray.get(i).getCreatedDate());
                            busList.add(bus);
                        }

//                        mAdapter = new WisataAdapter(list,MainActivity.this);

                        uAdapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ListBusActivity.this, "Gagal Mengambil Data",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Bus>> call, Throwable throwable) {
                Toast.makeText(ListBusActivity.this, throwable.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        doFetchBuses();
    }


}
