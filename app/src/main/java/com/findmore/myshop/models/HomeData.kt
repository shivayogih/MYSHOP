package com.findmore.myshop.models

import androidx.lifecycle.MutableLiveData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Shivayogi Hiremath on 06,June,2020
 */
class HomeData : MutableLiveData<HomeData?>(), Serializable {
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null

    @SerializedName("tokenid")
    @Expose
    var tokenid: String? = null

    @SerializedName("loginStatus")
    @Expose
    var loginStatus: Boolean? = null

    @SerializedName("isActive")
    @Expose
    var isActive: Boolean? = null

    @SerializedName("email_id")
    @Expose
    var emailId: String? = null

    @SerializedName("bannerItems")
    @Expose
    var bannerItems: BannerItems? = null

    @SerializedName("new_arrivals")
    @Expose
    var newArrivals: List<NewArrival>? = null

    @SerializedName("products")
    @Expose
    var products: List<Products>? = null

    @SerializedName("customer_like_categories")
    @Expose
    var likeCategories: List<LikeCategories>? = null

}