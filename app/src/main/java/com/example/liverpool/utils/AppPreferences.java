package com.example.liverpool.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.liverpool.R;
import com.example.liverpool.api.ApiClient;

public class AppPreferences {

    SharedPreferences sharedpreferences;
    private  SharedPreferences.Editor editor;
    private String search  = "search";

    private static AppPreferences instance = null;

    public static AppPreferences getInstance(Context context)
    {
        if (instance == null)
            instance = new AppPreferences(context);

        return instance;
    }

    public AppPreferences(Context context){
        sharedpreferences = context.getSharedPreferences(
                "com.example.liverpool",
                Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public void clearSearch() {
        editor.remove(this.search).commit();
    }

    public String getSearch() {
        return sharedpreferences.getString(this.search, "");
    }

    public void setSearch(String search) {
        editor.putString(this.search, search);
        editor.apply();
    }
}
