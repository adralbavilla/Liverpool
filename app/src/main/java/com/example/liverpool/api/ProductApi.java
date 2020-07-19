package com.example.liverpool.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductApi {

    @GET("appclienteservices/services/v3/plp")
    Call<JsonObject> getProducts(
            @Query("force-plp") Boolean force,
            @Query("search-string") String search,
            @Query("page-number") int page,
            @Query("number-of-items-per-page") int numberItems);
}
