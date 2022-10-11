package com.pillgood.drholmes.map;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pillgood.drholmes.R;
import com.pillgood.drholmes.map.hospital.MapHospitalActivity;
import com.pillgood.drholmes.map.pharmacy.MapPharmacyActivity;
import com.google.android.material.tabs.TabLayout;

public class MapActivity extends Fragment {

    MapHospitalActivity hospitalActivity;
    MapPharmacyActivity pharmacyActivity;
    View view;
    TabLayout tabs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_map, container, false);

        hospitalActivity = new MapHospitalActivity();
        pharmacyActivity = new MapPharmacyActivity();

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tab_map, hospitalActivity).commit();

        tabs = (TabLayout) view.findViewById(R.id.tabs_map);
        tabs.addTab(tabs.newTab().setText("병원"));
        tabs.addTab(tabs.newTab().setText("약국"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment selected = null;
                switch (tab.getPosition()) {
                    case 0:
                        selected = hospitalActivity;
                        break;
                    case 1:
                        selected = pharmacyActivity;
                        break;
                }
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tab_map, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }


}