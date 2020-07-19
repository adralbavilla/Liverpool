package com.example.liverpool.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Product implements Serializable {
    @SerializedName("productId")
    public String productId;
    @SerializedName("productDisplayName")
    public String productDisplayName;
    @SerializedName("listPrice")
    public Double listPrice;
    @SerializedName("smImage")
    public String smImage;
    @SerializedName("productAvgRating")
    public Float rating;

    public Product(String productId, String productDisplayName, Double listPrice, String smImage, Float rating) {
        this.productId = productId;
        this.productDisplayName = productDisplayName;
        this.listPrice = listPrice;
        this.smImage = smImage;
        this.rating = rating;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductDisplayName() {
        return productDisplayName;
    }

    public void setProductDisplayName(String productDisplayName) {
        this.productDisplayName = productDisplayName;
    }

    public Double getListPrice() {
        return listPrice;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    public String getSmImage() {
        return smImage;
    }

    public void setSmImage(String smImage) {
        this.smImage = smImage;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

}
