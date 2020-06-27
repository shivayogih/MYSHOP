package com.findmore.myshop.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Shivayogi Hiremath on 06,June,2020
 *
 */
class LimitedOffers : Serializable {
    @SerializedName("offer_id")
    @Expose
    var offerId: Int? = null

    @SerializedName("offer_type")
    @Expose
    var offerType: String? = null

    @SerializedName("offer_name")
    @Expose
    var offerName: String? = null

    @SerializedName("offer_descriptions")
    @Expose
    var offerDescriptions: String? = null

    @SerializedName("offer_image")
    @Expose
    var offerImage: Any? = null

    companion object {
        private const val serialVersionUID = -3855655471811924735L
    }
}