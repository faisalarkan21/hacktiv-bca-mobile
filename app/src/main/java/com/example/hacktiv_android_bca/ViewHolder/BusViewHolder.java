package com.example.hacktiv_android_bca.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hacktiv_android_bca.R;

public class BusViewHolder extends RecyclerView.ViewHolder {
    public TextView busCode, capity, model ;
    public BusViewHolder(View itemView) {
        super(itemView);
        busCode = (TextView) itemView.findViewById(R.id.bus_code);
        capity = (TextView) itemView.findViewById(R.id.bus_capity);
        model = (TextView) itemView.findViewById(R.id.bus_model);

    }
}
