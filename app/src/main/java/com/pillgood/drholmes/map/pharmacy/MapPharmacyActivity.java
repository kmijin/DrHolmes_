package com.pillgood.drholmes.map.pharmacy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pillgood.drholmes.R;

public class MapPharmacyActivity extends Fragment {
    Button listButton;
    View.OnClickListener cl;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_map_pharmacy, container, false);
        listButton = (Button) view.findViewById(R.id.pharmacy_list_button);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.pharmacy_list_button:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tab_map, new MapPharmacyListActivity()).commit();
                        break;
                }
            }
        };

        listButton.setOnClickListener(cl);
        return view;
    }
}