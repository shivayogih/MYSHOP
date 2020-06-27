package com.findmore.myshop.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Shivayogi Hiremath on 10,June,2020
 *
 */
class UserProfile : Serializable {
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null

    @SerializedName("tokenid")
    @Expose
    var tokenid: String? = null

    @SerializedName("firstName")
    @Expose
    var firstName: String? = null

    @SerializedName("lastName")
    @Expose
    var lastName: String? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("email_id")
    @Expose
    var emailId: String? = null

    @SerializedName("phone_number")
    @Expose
    var phoneNumber: String? = null

    @SerializedName("countryCode")
    @Expose
    var countryCode: Int? = null

    @SerializedName("age")
    @Expose
    var age: Int? = null

    @SerializedName("isActive")
    @Expose
    var isActive: Boolean? = null

    @SerializedName("address")
    @Expose
    var address: List<Address>? = null

}