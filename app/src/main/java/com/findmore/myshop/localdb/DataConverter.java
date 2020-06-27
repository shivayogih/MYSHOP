package com.findmore.myshop.localdb;


import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.findmore.myshop.models.Products;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class DataConverter implements Serializable {


    @TypeConverter // note this annotation
    public String fromOptionValuesList(Products products) {
        if (products == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Products>() {
        }.getType();
        String json = gson.toJson(products, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<Products> toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Products>() {
        }.getType();
        List<Products> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }



}