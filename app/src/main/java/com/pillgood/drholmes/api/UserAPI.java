package com.pillgood.drholmes.api;

import com.pillgood.drholmes.api.login.UserClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {
    String baseURL = "http://43.200.60.75:8080/";

    //로그인
    @POST("member/select")
    Call<List<UserClass>> userLogin(@Body UserClass userClass);

    //회원가입
    @POST("member/select")
    Call<List<UserClass>> userJoin(@Body UserClass userClass);
}