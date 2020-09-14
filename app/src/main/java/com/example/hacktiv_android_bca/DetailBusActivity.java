package com.example.hacktiv_android_bca;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.hacktiv_android_bca.Entity.Bus;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetailBusActivity extends AppCompatActivity {

    public TextView busCode, capity, model, createdDate ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bus);

        Bus bus = (Bus) getIntent().getSerializableExtra("busDetail");

        busCode = findViewById(R.id.bus_code);
        capity = findViewById(R.id.bus_capity);
        model = findViewById(R.id.bus_make);
        createdDate = findViewById(R.id.bus_created_date);

        busCode.setText(bus.getCode());
        capity.setText( String.valueOf( bus.getCapacity()));
        model.setText(bus.getMake());
        Timestamp ts=new Timestamp(bus.getCreatedDate());
        Date date=new Date(ts.getTime());

        String pattern = "dd MMMM yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern);

        createdDate.setText(simpleDateFormat.format(date));





    }
}
