package com.pillgood.drholmes.api;

import com.pillgood.drholmes.api.device.DeviceClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DeviceAPI {
    String baseURL = "http://43.200.60.75:8080/";

    @POST("pill")
    Call<DeviceClass> postData(@Body DeviceClass deviceClass);
}