package com.pillgood.drholmes.api;

import com.pillgood.drholmes.api.pharmacy.ResponseClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PharmacyAPI {
    String baseURL = "http://apis.data.go.kr/B551182/";
    String getPharmacy = "pharmacyInfoService/getParmacyBasisList";

    @GET(getPharmacy)
    Call<ResponseClass> getPharmacyInfo(@Query("serviceKey") String serviceKey);
}