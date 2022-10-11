package com.pillgood.drholmes.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pillgood.drholmes.R;

public class SettingActivity extends AppCompatActivity {

    ImageView Modify;
    Button Logout;
    View.OnClickListener cl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Modify = (ImageView) findViewById(R.id.Modify);
        Logout = (Button) findViewById(R.id.Logout);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };
        Logout.setOnClickListener(cl);
    }
}
