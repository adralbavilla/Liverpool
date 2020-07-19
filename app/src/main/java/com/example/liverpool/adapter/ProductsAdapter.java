package com.example.liverpool.adapter;

import android.content.Context;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liverpool.R;
import com.example.liverpool.model.Product;
import com.squareup.picasso.Picasso;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> implements Filterable {

    public List<Product> productList;
    public Context context;

    public ProductsAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull

    @Override
    public ProductsAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ProductsAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ProductViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        String decimal = new BigDecimal(productList.get(position).getListPrice()).setScale(2, RoundingMode.HALF_EVEN).toString();
        holder.productName.setText(productList.get(position).getProductDisplayName());
        holder.listPrice.setText("$" + decimal);
        Picasso.with(context).load(productList.get(position).getSmImage()).resize(120, 60).into(holder.imgProduct);
        holder.ratingBar.setRating(productList.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public Filter getFilter() {
        return productListFilter;
    }

    private Filter productListFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Product> listFilter = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                listFilter.addAll(productList);
            }else{
                String filter = constraint.toString().toLowerCase().trim();

                for(Product item : productList){
                    if(item.getProductDisplayName().toLowerCase().contains(filter)){
                        listFilter.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = listFilter;
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productList.clear();
            productList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView productName;
        TextView listPrice;
        RatingBar ratingBar;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            listPrice = itemView.findViewById(R.id.listPrice);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            ratingBar = itemView.findViewById(R.id.ratingBar);

        }
    }

}
