package com.example.hacktiv_android_bca.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hacktiv_android_bca.Adapter.BusAdapter;
import com.example.hacktiv_android_bca.Entity.Bus;
import com.example.hacktiv_android_bca.ListBusActivity;
import com.example.hacktiv_android_bca.R;
import com.example.hacktiv_android_bca.service.BusService;
import com.example.hacktiv_android_bca.service.UtilsApi;
import com.example.hacktiv_android_bca.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class BusesFragment extends Fragment {

    private List<Bus> busList = new ArrayList<>();
    private BusAdapter uAdapter;
    RecyclerView recyclerView;
    SessionManager session;
    BusService busService;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        doFetchBuses();
        View root = inflater.inflate(R.layout.fragment_buses, container, false);


        uAdapter = new BusAdapter(busList, getActivity());
        recyclerView = root.findViewById(R.id.bus_recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(uAdapter);

        return root;
    }

    public void doFetchBuses() {
        session = new SessionManager(getActivity());
        busService = UtilsApi.getBusAPIService();
        String test = session.getByKey("agencyId");
        Call<ArrayList<Bus>> response = busService.getBuses(session.getByKey("agencyId"));

        response.enqueue(new Callback<ArrayList<Bus>>() {
            @Override
            public void onResponse(Call<ArrayList<Bus>> call, retrofit2.Response<ArrayList<Bus>> rawResponse) {
                if (rawResponse.isSuccessful()) {
                    try {
                        ArrayList<Bus> jsonArray = rawResponse.body();

                        if (jsonArray.size() == 0) {
                            Toast.makeText(getActivity(), "Tidak ada data.",
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
                    Toast.makeText(getActivity(), "Gagal Mengambil Data",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Bus>> call, Throwable throwable) {
                Toast.makeText(getActivity(), throwable.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }


}
