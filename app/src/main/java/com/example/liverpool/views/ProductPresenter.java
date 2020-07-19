package com.example.liverpool.views;

import android.util.Log;

import com.example.liverpool.api.ApiClient;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPresenter implements ProductContract.Presenter{

    public ProductContract.View view;

    public ProductPresenter(ProductContract.View view){
        this.view = view;
    }

    @Override
    public void getProduct(Boolean force, String search, int page, int numberItems) {
        Call<JsonObject> client = ApiClient.getInstance().getProducts(force, search, page, numberItems);
        client.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Log.e("Data", response.body().toString());
                    view.getProduct(response.body());
                } else {

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("Error",t.getCause().toString());
            }
        });
    }
}
