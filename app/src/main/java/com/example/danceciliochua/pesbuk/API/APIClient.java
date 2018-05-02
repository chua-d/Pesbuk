package com.example.danceciliochua.pesbuk.API;

import com.example.danceciliochua.pesbuk.Data.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIClient {

    @GET("/users")
    Call<List<Users>> listAddress();

    @GET("/users")
    Call<List<Users>> listUsers();

    @GET("/users")
    Call<List<Users>> profile(@Query("id") Integer id);

}

