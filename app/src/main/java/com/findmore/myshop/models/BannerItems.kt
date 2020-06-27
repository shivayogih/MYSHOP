package com.findmore.myshop.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Shivayogi Hiremath on 06,June,2020
 *
 */
class BannerItems : Serializable {
    @SerializedName("limited_offers")
    @Expose
    var limitedOffers: List<LimitedOffers>? = null

    @SerializedName("new_arrivals")
    @Expose
    var newArrivals: List<NewArrival>? = null

}