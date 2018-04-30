package com.example.danceciliochua.pesbuk.API;

import com.example.danceciliochua.pesbuk.Data.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIClient {

    @GET("/users")
    Call<List<Users>> listAddress();

    @GET("/users")
    Call<List<Users>> listUsers();

}

