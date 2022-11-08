package com.pillgood.drholmes.info;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.GsonBuilder;
import com.pillgood.drholmes.R;
import com.pillgood.drholmes.api.PillAPI;
import com.pillgood.drholmes.api.pill.ItemClass;
import com.pillgood.drholmes.api.pill.ResponseClass;
import com.pillgood.drholmes.map.pharmacy.Pharmacy;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoActivity extends Fragment {

    View view;

    RecyclerView recyclerView;
    PillAdapter adapter;

    Retrofit retrofit;
    PillAPI service;
    String serviceKey_origin = "J%2FS0JBdWnrQa9KR69M9AJHWjQwTch0%2F20l8%2BdpQ5wH8sMuKGfYlihZjIxwDCPjVBF9JUeaTeJr1xEhbDvcL%2BWw%3D%3D";
    String serviceKey;

    SearchView searchView;
    SearchView.OnQueryTextListener searchViewTextListener;

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

        view = inflater.inflate(R.layout.activity_info, container, false);

        recyclerView = view.findViewById(R.id.pill_list_recycle);

        searchView = view.findViewById(R.id.pill_search);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PillAdapter();

        recyclerView.setAdapter(adapter);

        retrofit = new Retrofit.Builder()
                .baseUrl(PillAPI.baseURL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();
        service = retrofit.create(PillAPI.class);

        service.getPillInfo(serviceKey, "json").enqueue(new Callback<ResponseClass>() {
            @Override
            public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                Log.e("약", "response=" + response.code() + " Total=" + response.body().getBody().getTotalCount());
                for (ItemClass pill : response.body().getBody().getItems()) {
                    Log.e("약", pill.getItemName());
                    adapter.addItem(new Pill(pill.getItemImage(), pill.getItemName(), pill.getClassName(), pill.getChart()));
                    adapter.notifyItemInserted(adapter.getItemCount());
                }
            }

            @Override
            public void onFailure(Call<ResponseClass> call, Throwable t) {
                Log.e("약", "ERROR=" + t.toString());

            }
        });

        searchViewTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                adapter.clearItems();
                adapter.notifyDataSetChanged();

                service.getPillInfo(serviceKey, "json", s).enqueue(new Callback<ResponseClass>() {
                    @Override
                    public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                        Log.e("약", "response=" + response.code() + " Total=" + response.body().getBody().getTotalCount());
                        for (ItemClass pill : response.body().getBody().getItems()) {
                            Log.e("약", pill.getItemName());
                            adapter.addItem(new Pill(pill.getItemImage(), pill.getItemName(), pill.getClassName(), pill.getChart()));
                            adapter.notifyItemInserted(adapter.getItemCount());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseClass> call, Throwable t) {
                        Log.e("약", "ERROR=" + t.toString());

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
