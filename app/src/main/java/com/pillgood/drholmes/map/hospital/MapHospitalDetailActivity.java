package com.pillgood.drholmes.map.hospital;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.LocationSource;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;
import com.pillgood.drholmes.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class MapHospitalDetailActivity extends Fragment implements OnMapReadyCallback {

    View view;
    String TAG = "MapHospitalDetailActivity";

    Button btnFindWay;
    View.OnClickListener cl;

    String hospitalName, hospitalAddress, hospitalTel;
    Double hospitalXPos, hospitalYPos;

    NaverMap naverMap;
    FusedLocationSource locationSource;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_map_hospital_detail, container, false);

        btnFindWay = view.findViewById(R.id.btnFindWayHospital);

        FragmentManager fm = getChildFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.mapview_hospital_detail);

        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.mapview_hospital_detail, mapFragment).commit();
        }

        locationSource = new FusedLocationSource(this.getActivity(), 1000);

        mapFragment.getMapAsync(this);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btnFindWayHospital:
                        String url = null;
                        try {
                            url = String.format("nmap://route/public?dlat=%s&dlng=%s&dname=%s&appname=%s", hospitalYPos, hospitalXPos, URLEncoder.encode(hospitalName, "UTF-8"), "com.pillgood.drholmes");
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

        getParentFragmentManager().setFragmentResultListener("hospital_selected", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                hospitalName = bundle.getString("hospital_name");
                hospitalAddress = bundle.getString("hospital_address");
                hospitalTel = bundle.getString("hospital_tel");
                hospitalXPos = bundle.getDouble("hospital_XPos");
                hospitalYPos = bundle.getDouble("hospital_YPos");

                ((TextView) view.findViewById(R.id.hospital_detail_name)).setText(hospitalName);
                ((TextView) view.findViewById(R.id.hospital_detail_address)).setText(hospitalAddress);
                ((TextView) view.findViewById(R.id.hospital_detail_tel)).setText(hospitalTel);
            }
        });

        return view;
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);

        naverMap.setCameraPosition(new CameraPosition(new LatLng(hospitalYPos, hospitalXPos), 15));
        Log.i("HOSPITAL", hospitalXPos.toString());

        Marker marker = new Marker();
        marker.setPosition(new LatLng(hospitalYPos, hospitalXPos));
        marker.setCaptionText(hospitalName);
        marker.setMap(naverMap);


    }
}
