package com.pillgood.drholmes.map.hospital;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.util.FusedLocationSource;
import com.pillgood.drholmes.R;

public class MapHospitalActivity extends Fragment  implements OnMapReadyCallback, LocationListener {
    Button listButton;
    View.OnClickListener cl;
    View view;

    GpsOnlyLocationSource locationSource;
    NaverMap naverMap;
    LocationManager locationManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_map_hospital, container, false);
        listButton = (Button) view.findViewById(R.id.hospital_list_button);
        locationSource = new GpsOnlyLocationSource(this.getActivity());

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.hospital_list_button:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tab_map, new MapHospitalListActivity()).commit();
                        break;
                }
            }
        };
        listButton.setOnClickListener(cl);

        FragmentManager fm = getChildFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.mapview_hospital);

        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.mapview_hospital, mapFragment).commit();
        }
        mapFragment.getMapAsync(naverMap1 -> naverMap = naverMap1);

        locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (locationManager != null) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 1000, 10, this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (naverMap == null || location == null) {
            return;
        }

        LatLng coord = new LatLng(location);

        LocationOverlay locationOverlay = naverMap.getLocationOverlay();
        locationOverlay.setVisible(true);
        locationOverlay.setPosition(coord);
        locationOverlay.setBearing(location.getBearing());

        naverMap.moveCamera(CameraUpdate.scrollTo(coord));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);

        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
    }
}