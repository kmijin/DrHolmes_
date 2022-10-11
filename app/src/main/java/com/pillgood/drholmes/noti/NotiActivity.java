package com.pillgood.drholmes.noti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.pillgood.drholmes.R;

import java.util.ArrayList;

public class NotiActivity extends AppCompatActivity {
    // 테스트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti);

        Intent fcm = new Intent(getApplicationContext(), MyFirebaseMessaging.class);
        startService(fcm);

        ArrayList<String> testDataSet = new ArrayList<>();
        for (int i = 0; i<20; i++) {
            testDataSet.add("TEST DATA" + i);
        }

        RecyclerView recyclerView = findViewById(R.id.noti_recycle);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
        recyclerView.setLayoutManager(linearLayoutManager);

        NotiCustomAdapter noticustomAdapter = new NotiCustomAdapter(testDataSet);
        recyclerView.setAdapter(noticustomAdapter);
    }
}