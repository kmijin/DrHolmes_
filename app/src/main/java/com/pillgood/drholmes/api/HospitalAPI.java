package com.pillgood.drholmes.api;

import com.pillgood.drholmes.api.hospital.ResponseClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HospitalAPI {
    String baseURL = "http://apis.data.go.kr/B551182/";
    String getHospital = "hospInfoServicev2/getHospBasisList";

    @GET(getHospital)
    Call<ResponseClass> getHospitalInfo(@Query("serviceKey") String serviceKey);

    @GET(getHospital)
    Call<ResponseClass> getHospitalInfo(@Query("serviceKey") String serviceKey,
                                        @Query("emdongNm") String emdongNm);

    @GET(getHospital)
    Call<ResponseClass> getHospitalInfo(@Query("serviceKey") String serviceKey,
                                        @Query("xPos") Double xPos,
                                        @Query("yPos") Double yPos,
                                        @Query("radius") Integer radius);
}