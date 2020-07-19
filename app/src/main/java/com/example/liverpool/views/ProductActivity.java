package com.example.liverpool.views;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liverpool.R;
import com.example.liverpool.adapter.ProductsAdapter;
import com.example.liverpool.model.Product;
import com.example.liverpool.utils.AppPreferences;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements ProductContract.View {

    RecyclerView recyclerView;
    ProductsAdapter adapter;
    ProductContract.Presenter presenter;
    List<Product> productos = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view_group);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        presenter = new ProductPresenter(this);
        if(AppPreferences.getInstance(getApplicationContext()).getSearch() != ""){
            presenter.getProduct(true,
                    AppPreferences.getInstance(getApplicationContext()).getSearch(),1,30);
            Log.e("Log",productos.toString());
        }else{
            AppPreferences.getInstance(getApplicationContext()).clearSearch();
        }
        }

    @Override
    protected void onRestart() {
        super.onRestart();
        AppPreferences.getInstance(getApplicationContext()).getSearch();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query != ""){
                    presenter.getProduct(true,query,1,30);
                    AppPreferences.getInstance(getApplicationContext()).setSearch(query);
                }else{
                    Toast.makeText(getApplication().getApplicationContext(), "Debe de escribir algo",Toast.LENGTH_SHORT).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public void getProduct(JsonObject object) {
        if(object != null){
            List<Product> listado = new ArrayList();
            JsonObject objectResults = object.getAsJsonObject("plpResults");
            JsonArray arrayResult = objectResults.getAsJsonArray("records");
            if(arrayResult.size() != 0){
                for (int i = 1; i < arrayResult.size(); i++) {
                    JsonObject objectJson = arrayResult.get(i).getAsJsonObject();
                    listado.add(new Product(objectJson.get("productId").getAsString(),
                            objectJson.get("productDisplayName").getAsString(),
                            objectJson.get("listPrice").getAsDouble(),
                            objectJson.get("smImage").getAsString(),
                            objectJson.get("productRatingCount").getAsFloat()));
                }
                Log.e("listado",listado.get(0).getSmImage());
                adapter = new ProductsAdapter(this, listado);
                adapter.setHasStableIds(true);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
            }else{
                Toast.makeText(this, "No hay datos en la búsqueda",Toast.LENGTH_SHORT).show();
            }

        }

    }
}
