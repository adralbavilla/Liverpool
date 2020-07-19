package com.example.liverpool.views;

import com.example.liverpool.model.Product;
import com.google.gson.JsonObject;

import java.util.List;

public interface ProductContract {

    interface View {
        void getProduct(JsonObject object);
    }

    interface Presenter {
        void getProduct(Boolean force, String search, int page, int numberItems);
    }

}
