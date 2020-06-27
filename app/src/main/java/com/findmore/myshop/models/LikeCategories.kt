package com.findmore.myshop.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Shivayogi Hiremath on 06,June,2020
 *
 */
class LikeCategories : Serializable {
    @SerializedName("category_id")
    @Expose
    var categoryId: Int? = null

    @SerializedName("category_label")
    @Expose
    var categoryLabel: String? = null

    @SerializedName("category_name")
    @Expose
    var categoryName: String? = null

    @SerializedName("category_image")
    @Expose
    var categoryImage: String? = null

    @SerializedName("category_descriptions")
    @Expose
    var categoryDescriptions: String? = null

}