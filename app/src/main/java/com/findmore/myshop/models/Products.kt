package com.findmore.myshop.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import java.io.Serializable

/**
 * Created by Shivayogi Hiremath on 06,June,2020
 */
@Entity
class Products : Serializable {
    @PrimaryKey
    @SerializedName("product_id")
    @Expose
    @NotNull
    var productId: String? = null

    @SerializedName("product_name")
    @Expose
    var productName: String? = null

    @SerializedName("product_label")
    @Expose
    var productLabel: String? = null

    @SerializedName("product_image")
    @Expose
    var productImage: String? = null

    @SerializedName("product_amount")
    @Expose
    var productAmount = 0

    @SerializedName("product_amount_currency")
    @Expose
    var productAmountCurrency: String? = null

    @SerializedName("product_isAvailable")
    @Expose
    var productIsAvailable: Boolean? = null

    @SerializedName("product_retailerid")
    @Expose
    var productRetailerid: String? = null

    @SerializedName("product_isfaverit")
    @Expose
    var productIsfaverit: Boolean? = null

    @SerializedName("product_categoryName")
    @Expose
    var productCategoryName: String? = null

    @SerializedName("product_categoryId")
    @Expose
    var productCategoryId: String? = null

    @SerializedName("product_specification")
    @Expose
    var productSpecification: String? = null

    @SerializedName("product_service_charges")
    @Expose
    var productServiceCharges: Int? = null

    @SerializedName("product_isadded_to_cart")
    @Expose
    var isAddedToCart = false

    @SerializedName("product_discount_offer")
    @Expose
    var productDiscountOffer: String? = null

    @SerializedName("product_discount_offer_amount")
    @Expose
    var productDiscountOfferAmount: Int? = null

}