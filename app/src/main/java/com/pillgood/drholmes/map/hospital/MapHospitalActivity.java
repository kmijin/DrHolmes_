package com.pillgood.drholmes.map.hospital;

import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.util.FusedLocationSource;
import com.pillgood.drholmes.R;
import com.pillgood.drholmes.api.HospitalAPI;
import com.pillgood.drholmes.api.hospital.ItemClass;
import com.pillgood.drholmes.api.hospital.ResponseClass;
import com.tickaroo.tikxml.TikXml;
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapHospitalActivity extends Fragment  implements OnMapReadyCallback{
    Button listButton;
    View.OnClickListener cl;
    View view;
    FragmentManager fragmentManager;
    MapHospitalDetailActivity hospitalDetailActivity;

    FusedLocationSource locationSource;
    NaverMap naverMap;
    Double lat, lon;


    List<Marker> markers = new ArrayList<>();

    Retrofit retrofit;
    HospitalAPI service;
    String serviceKey_origin = "J%2FS0JBdWnrQa9KR69M9AJHWjQwTch0%2F20l8%2BdpQ5wH8sMuKGfYlihZjIxwDCPjVBF9JUeaTeJr1xEhbDvcL%2BWw%3D%3D";
    String serviceKey;

    HospitalAdapter adapter;

    {
        try {
            serviceKey = URLDecoder.decode(serviceKey_origin, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getParentFragmentManager();
        hospitalDetailActivity = new MapHospitalDetailActivity();

        view = inflater.inflate(R.layout.activity_map_hospital, container, false);
        listButton = (Button) view.findViewById(R.id.hospital_list_button);

        locationSource = new FusedLocationSource(this.getActivity(), 1000);

        adapter = new HospitalAdapter();

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
        mapFragment.getMapAsync(this);




        return view;
    }


    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);
        Log.e("LOCATION", "Map Ready");


        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

        naverMap.addOnCameraIdleListener(new NaverMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                if (!markers.isEmpty()) {
                    for (int i = 0; i < markers.size(); i++) {
                        markers.get(i).setMap(null);
                    }
                }

                LatLng latlng = naverMap.getCameraPosition().target;
                lat = latlng.latitude;
                lon = latlng.longitude;

                requestAPI(lat, lon);
            }
        });

    }

    public void requestAPI(Double lat, Double lon) {
        try {
            retrofit = new Retrofit.Builder()
                    .baseUrl(HospitalAPI.baseURL)
                    .addConverterFactory(TikXmlConverterFactory.create(new TikXml.Builder().exceptionOnUnreadXml(false).build()))
                    .build();
            service = retrofit.create(HospitalAPI.class);

            service.getHospitalInfo(serviceKey, lon, lat,2000).enqueue(new Callback<ResponseClass>() {
                @Override
                public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                    if (response.isSuccessful()) {
                        Log.e("병원", "response=" + response.code() + " Total=" + response.body().getBody().getTotalCount());
                        if (response.body().getBody().getTotalCount() == 0) {
                            Log.e("병원", "주변 병원 없음");
                        } else {
                            for(ItemClass item : response.body().getBody().getItems().getItem()) {
                                Log.e("병원", item.getYadmNm());
//                                adapter.addItem(new Hospital(item.getYadmNm(), item.getClCdNm(),item.getAddr(), item.getTelno(), item.getXPos(), item.getYPos()));
//                                adapter.notifyItemInserted(adapter.getItemCount());
                                Marker marker = new Marker();
                                marker.setPosition(new LatLng(item.getYPos(), item.getXPos()));
                                marker.setCaptionText(item.getYadmNm());
                                marker.setMap(naverMap);

                                marker.setOnClickListener(new Overlay.OnClickListener() {
                                    @Override
                                    public boolean onClick(@NonNull Overlay overlay) {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("hospital_name", item.getYadmNm());
                                        bundle.putString("hospital_address", item.getAddr());
                                        bundle.putString("hospital_tel", item.getTelno());
                                        bundle.putDouble("hospital_XPos", item.getXPos());
                                        bundle.putDouble("hospital_YPos", item.getYPos());

                                        fragmentManager.setFragmentResult("hospital_selected", bundle);
                                        fragmentManager.beginTransaction().replace(R.id.fragment_tab_map, hospitalDetailActivity).commit();
                                        return false;
                                    }
                                });

                                markers.add(marker);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseClass> call, Throwable t) {
                    Log.e("병원", "ERROR=" + t.toString());
                }
            });
        } catch (Exception e) {
            Log.e("병원 Exception", e.getMessage());
        }
    }
}