package com.pillgood.drholmes.map.pharmacy;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.pillgood.drholmes.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class MapPharmacyDetailActivity extends Fragment {

    View view;
    String TAG = "MapPharmacyDetailActivity";

    Button btnFindWay;
    View.OnClickListener cl;

    String pharmacyName, pharmacyAddress, pharmacyTel;
    Double pharmacyXPos, pharmacyYPos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_map_pharmacy_detail, container, false);

        btnFindWay = view.findViewById(R.id.btnFindWayPharmacy);
        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btnFindWayPharmacy:
                        String url = null;
                        try {
                            url = String.format("nmap://route/public?dlat=%s&dlng=%s&dname=%s&appname=%s", pharmacyYPos, pharmacyXPos, URLEncoder.encode(pharmacyName, "UTF-8"), "com.pillgood.drholmes");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);

                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.nhn.android.nmap")));
                        }

                        break;
                }
            }
        };
        btnFindWay.setOnClickListener(cl);

        getParentFragmentManager().setFragmentResultListener("pharmacy_selected", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                pharmacyName = bundle.getString("pharmacy_name");
                pharmacyAddress = bundle.getString("pharmacy_address");
                pharmacyTel = bundle.getString("pharmacy_tel");
                pharmacyXPos = bundle.getDouble("pharmacy_XPos");
                pharmacyYPos = bundle.getDouble("pharmacy_YPos");

                ((TextView) view.findViewById(R.id.pharmacy_detail_name)).setText(pharmacyName);
                ((TextView) view.findViewById(R.id.pharmacy_detail_address)).setText(pharmacyAddress);
                ((TextView) view.findViewById(R.id.pharmacy_detail_tel)).setText(pharmacyTel);
            }
        });

        return view;
    }
}
