package com.pillgood.drholmes.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.pillgood.drholmes.R;
import com.pillgood.drholmes.info.disease.InfoDiseaseActivity;
import com.pillgood.drholmes.info.pill.InfoPillActivity;

public class InfoActivity extends Fragment {

    InfoDiseaseActivity diseaseActivity;
    InfoPillActivity pillActivity;
    View view;
    TabLayout tabs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_info, container, false);

        diseaseActivity = new InfoDiseaseActivity();
        pillActivity = new InfoPillActivity();

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tab_info, diseaseActivity).commit();

        tabs = (TabLayout) view.findViewById(R.id.tabs_info);
        tabs.addTab(tabs.newTab().setText("질환"));
        tabs.addTab(tabs.newTab().setText("약"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment selected = null;
                switch (tab.getPosition()) {
                    case 0:
                        selected = diseaseActivity;
                        break;
                    case 1:
                        selected = pillActivity;
                        break;
                }
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tab_info, selected).commit();
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
