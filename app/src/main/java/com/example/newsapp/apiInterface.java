package com.example.newsapp;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface apiInterface {

    @GET("v2/everything")
    Call<String> getNews(@QueryMap Map<String, Object> queries);

}
