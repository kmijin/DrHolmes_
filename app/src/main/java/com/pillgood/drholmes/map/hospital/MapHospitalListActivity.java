package com.pillgood.drholmes.map.hospital;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naver.maps.map.util.FusedLocationSource;
import com.pillgood.drholmes.R;
import com.pillgood.drholmes.api.HospitalAPI;
import com.pillgood.drholmes.api.hospital.ItemClass;
import com.pillgood.drholmes.api.hospital.ResponseClass;
import com.tickaroo.tikxml.TikXml;
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapHospitalListActivity extends Fragment {

    Button mapButton;
    View.OnClickListener cl;
    FragmentManager fragmentManager;
    MapHospitalActivity hospitalActivity;
    MapHospitalDetailActivity hospitalDetailActivity;
    View view;
    RecyclerView recyclerView;
    HospitalAdapter adapter;

    Retrofit retrofit;
    HospitalAPI service;
    String serviceKey_origin = "J%2FS0JBdWnrQa9KR69M9AJHWjQwTch0%2F20l8%2BdpQ5wH8sMuKGfYlihZjIxwDCPjVBF9JUeaTeJr1xEhbDvcL%2BWw%3D%3D";
    String serviceKey;

    FusedLocationSource locationSource;

    SearchView searchView;
    SearchView.OnQueryTextListener searchViewTextListener;

    {
        try {
            serviceKey = URLDecoder.decode(serviceKey_origin, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_map_hospital_list, container, false);

        mapButton = (Button) view.findViewById(R.id.to_map_hospital_button);

        searchView = view.findViewById(R.id.hospital_list_search);

        hospitalActivity = new MapHospitalActivity();

        fragmentManager = getParentFragmentManager();

        locationSource = new FusedLocationSource(this.getActivity(), 1000);


        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.to_map_hospital_button:
                        fragmentManager.beginTransaction().replace(R.id.fragment_tab_map, hospitalActivity).commit();
                        break;
                }
            }
        };
        mapButton.setOnClickListener(cl);

        hospitalDetailActivity = new MapHospitalDetailActivity();

        recyclerView = view.findViewById(R.id.hospital_list_recycle);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HospitalAdapter();

        adapter.setOnItemClickListener(new HospitalAdapter.OnPharmacyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Hospital hospital = adapter.getItem(position);

                Bundle bundle = new Bundle();
                bundle.putString("hospital_name", hospital.getName());
                bundle.putString("hospital_address", hospital.getAddress());
                bundle.putString("hospital_tel", hospital.getTel());
                bundle.putDouble("hospital_XPos", hospital.getXPos());
                bundle.putDouble("hospital_YPos", hospital.getYPos());

                fragmentManager.setFragmentResult("hospital_selected", bundle);
                fragmentManager.beginTransaction().replace(R.id.fragment_tab_map, hospitalDetailActivity).commit();
            }
        });

        recyclerView.setAdapter(adapter);

        try {
            retrofit = new Retrofit.Builder()
                    .baseUrl(HospitalAPI.baseURL)
                    .addConverterFactory(TikXmlConverterFactory.create(new TikXml.Builder().exceptionOnUnreadXml(false).build()))
                    .build();
            service = retrofit.create(HospitalAPI.class);

            service.getHospitalInfo(serviceKey).enqueue(new Callback<ResponseClass>() {
                @Override
                public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                    if (response.isSuccessful()) {
                        Log.e("??????", "response=" + response.code() + " Total=" + response.body().getBody().getTotalCount());
                        for(ItemClass item : response.body().getBody().getItems().getItem()) {
                            Log.e("??????", item.getYadmNm());
                            adapter.addItem(new Hospital(item.getYadmNm(), item.getClCdNm(),item.getAddr(), item.getTelno(), item.getXPos(), item.getYPos()));
                            adapter.notifyItemInserted(adapter.getItemCount());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseClass> call, Throwable t) {
                    Log.e("??????", "ERROR=" + t.toString());
                }
            });
        } catch (Exception e) {
            Log.e("?????? Exception", e.getMessage());
        }

        searchViewTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                adapter.clearItems();
                adapter.notifyDataSetChanged();

                service.getHospitalInfo(serviceKey, s).enqueue(new Callback<ResponseClass>() {
                    @Override
                    public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                        if (response.isSuccessful()) {
                            Log.e("??????", "response=" + response.code() + " Total=" + response.body().getBody().getTotalCount());
                            for(ItemClass item : response.body().getBody().getItems().getItem()) {
                                Log.e("??????", item.getYadmNm());
                                adapter.addItem(new Hospital(item.getYadmNm(), item.getClCdNm(),item.getAddr(), item.getTelno(), item.getXPos(), item.getYPos()));
                                adapter.notifyItemInserted(adapter.getItemCount());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseClass> call, Throwable t) {
                        Log.e("??????", "ERROR=" + t.toString());
                    }
                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        };

        searchView.setOnQueryTextListener(searchViewTextListener);


        return view;
    }
}