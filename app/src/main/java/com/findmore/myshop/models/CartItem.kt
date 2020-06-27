package com.findmore.myshop.models

import androidx.lifecycle.MutableLiveData
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Shivayogi Hiremath on 06,June,2020
 *
 */
@Entity
class CartItem : Serializable {
    @SerializedName("cart_products")
    @Expose
    val products =
        MutableLiveData<List<CartProducts>>()

    @SerializedName("isOrdered")
    @Expose
    var ordered = false

    @SerializedName("total_cart_items")
    @Expose
    var total_cart_items = 0

    @SerializedName("total_order_amount")
    @Expose
    var total_amount = 0

    fun setProducts(products: List<CartProducts>) {
        this.products.value = products
    }

}