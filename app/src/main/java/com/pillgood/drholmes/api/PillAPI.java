package com.pillgood.drholmes.api;

import com.pillgood.drholmes.api.pill.BodyClass;
import com.pillgood.drholmes.api.pill.ResponseClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PillAPI {
    String baseURL = "http://apis.data.go.kr/1471000/MdcinGrnIdntfcInfoService01/";

    @GET("getMdcinGrnIdntfcInfoList01")
    Call<ResponseClass> getPillInfo(
            @Query("serviceKey") String serviceKey,
            @Query("type") String type);
}