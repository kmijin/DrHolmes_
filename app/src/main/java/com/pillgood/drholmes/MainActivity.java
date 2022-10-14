package com.pillgood.drholmes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import com.google.firebase.messaging.FirebaseMessaging;
import com.pillgood.drholmes.check.CheckActivity;
import com.pillgood.drholmes.device.DeviceActivity;
import com.pillgood.drholmes.home.HomeActivity;
import com.pillgood.drholmes.info.InfoActivity;
import com.pillgood.drholmes.map.MapActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Menu menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        menu = bottomNavigationView.getMenu();

        // 처음 화면
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new HomeActivity()).commit(); // FrameLayout에 activity_home 띄우기
        menu.findItem(R.id.fragment_navi_home).setIcon(R.drawable.ic_menu_home_fill);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.fragment_navi_home:
                        setMenuItemUnselected();
                        item.setIcon(R.drawable.ic_menu_home_fill);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new HomeActivity()).commit();
                        break;
                    case R.id.fragment_navi_check:
                        setMenuItemUnselected();
                        item.setIcon(R.drawable.ic_menu_check_fill);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new CheckActivity()).commit();
                        break;
                    case R.id.fragment_navi_info:
                        setMenuItemUnselected();
                        item.setIcon(R.drawable.ic_menu_info_fill);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new InfoActivity()).commit();
                        break;
                    case R.id.fragment_navi_map:
                        setMenuItemUnselected();
                        item.setIcon(R.drawable.ic_menu_map_fill);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MapActivity()).commit();
                        break;
                    case R.id.fragment_navi_device:
                        setMenuItemUnselected();
                        item.setIcon(R.drawable.ic_menu_device_fill);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new DeviceActivity()).commit();
                        break;
                }
                return true;
            }
        });
    }

    public void setMenuItemUnselected() {
        menu.findItem(R.id.fragment_navi_home).setIcon(R.drawable.ic_menu_home_stroke);
        menu.findItem(R.id.fragment_navi_check).setIcon(R.drawable.ic_menu_check_stroke);
        menu.findItem(R.id.fragment_navi_info).setIcon(R.drawable.ic_menu_info_stroke);
        menu.findItem(R.id.fragment_navi_map).setIcon(R.drawable.ic_menu_map_stroke);
        menu.findItem(R.id.fragment_navi_device).setIcon(R.drawable.ic_menu_device_stroke);
    }

}
