package com.example.liverpool.api;

import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.logging.HttpLoggingInterceptor;

public class ApiClient {

    private Retrofit retrofit;

    private static ApiClient instance = null;

    public static ApiClient getInstance()
    {
        if (instance == null)
            instance = new ApiClient();

        return instance;
    }

    public ApiClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client =
                new OkHttpClient.Builder().
                        addInterceptor(interceptor)
                        .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://shoppapp.liverpool.com.mx/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

    }

    public Call<JsonObject> getProducts(Boolean force, String search, int page, int numberItems) {
        ProductApi api = retrofit.create(ProductApi.class);
        return api.getProducts(force, search, page, numberItems);
    }


}
