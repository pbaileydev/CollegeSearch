package com.pbaileyapps.android.collegesearch.RetrofitComponents;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitInterface {
    @GET
    Call<JsonArray> locationFinder(@Url String url);
}
