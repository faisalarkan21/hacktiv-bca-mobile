package com.example.hacktiv_android_bca.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hacktiv_android_bca.DetailBusActivity;
import com.example.hacktiv_android_bca.Entity.Bus;
import com.example.hacktiv_android_bca.ListBusActivity;
import com.example.hacktiv_android_bca.R;
import com.example.hacktiv_android_bca.ViewHolder.BusViewHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class BusAdapter extends RecyclerView.Adapter<BusViewHolder> {

    private List<Bus> busList;
    private Context mContext;

    public BusAdapter(List<Bus> busList, Context activity) {
        this.mContext = activity;
        this.busList = busList;
    }


    @NonNull
    @Override
    public BusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_bus, parent, false);

        return new BusViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BusViewHolder holder, int position) {

        holder.busCode.setText(busList.get(position).getCode());
        holder.capity.setText(String.valueOf(busList.get(position).getCapacity()));
        holder.model.setText(busList.get(position).getMake());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent listBus = new Intent(mContext, DetailBusActivity.class);
                listBus.putExtra("busDetail",  busList.get(position));
                mContext.startActivity(listBus);


            }
        });
    }

    @Override
    public int getItemCount() {
        return busList.size();
    }
}
