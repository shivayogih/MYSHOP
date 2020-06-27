package com.findmore.myshop.localdb;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.findmore.myshop.models.CartProducts;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
@TypeConverters(DataConverter.class)
public interface CartModelDao {

    @Query("select * from CartProducts")
    List<CartProducts> getAllCartProductItems();

    @Query("select * from CartProducts where productId = :id")
    CartProducts getItembyId(String id);

    @Insert(onConflict = REPLACE)
    void addProducts(CartProducts cartProducts);


    @Update(onConflict = REPLACE)
    void updateProducts(CartProducts cartProducts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllProducts(List<CartProducts> cartProductList);

    @Delete
    void deleteProduct(CartProducts cartProducts);

    @Query("DELETE FROM CartProducts")
    void deleteAll();

}
