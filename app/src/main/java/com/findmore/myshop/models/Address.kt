package com.findmore.myshop.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Shivayogi Hiremath on 10,June,2020
 *
 */
class Address : Serializable {
    @SerializedName("address_line")
    @Expose
    var addressLine: String? = null

    @SerializedName("streetAddress")
    @Expose
    var streetAddress: String? = null

    @SerializedName("address_tag")
    @Expose
    var addressTag: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("state")
    @Expose
    var state: String? = null

    @SerializedName("isshipping_address")
    @Expose
    var isshippingAddress: Boolean? = false

    @SerializedName("postalCode")
    @Expose
    var postalCode: Int? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("location")
    @Expose
    var location: List<Double>? = null

}