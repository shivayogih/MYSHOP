package com.findmore.myshop.models;

/**
 * Created by Shivayogi Hiremath on 06,June,2020
 */

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class CartProducts implements Serializable
{

    @NonNull
    public String getCart_productId() {
        return cart_productId;
    }

    public void setCart_productId(@NonNull String cart_productId) {
        this.cart_productId = cart_productId;
    }

    @PrimaryKey

    @SerializedName("cart_product_id")
    @Expose
    @NonNull
    private String cart_productId;

    @Embedded
    @SerializedName("product")
    @Expose
    private  Products products;

    @SerializedName("product_quantity")
    @Expose
    private  int product_quantity = 0;

    @SerializedName("product_total_amount")
    @Expose
    private  int product_total_amount = 0;

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
        this.cart_productId=products.getProductId();
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public int getProduct_total_amount() {
        product_total_amount=  product_quantity*getProducts().getProductAmount();
        return product_total_amount;
    }

    public void setProduct_total_amount(int product_total_amount) {
        this.product_total_amount = product_total_amount;
    }


}