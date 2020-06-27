package com.findmore.myshop.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Shivayogi Hiremath on 06,June,2020
 *
 */
class NewArrival : Serializable {
    @SerializedName("product_id")
    @Expose
    var productId: Int? = null

    @SerializedName("product_categoryname")
    @Expose
    var productCategoryname: String? = null

    @SerializedName("product_categoryid")
    @Expose
    var productCategoryid: String? = null

    @SerializedName("product_labe")
    @Expose
    var productLabe: String? = null

    @SerializedName("product_image")
    @Expose
    var productImage: String? = null

}