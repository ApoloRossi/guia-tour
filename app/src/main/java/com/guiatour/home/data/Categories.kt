package com.guiatour.home.data

import com.google.gson.annotations.SerializedName

data class Categories(
    val category: String,

    @SerializedName("groups_by_category")
    val groupsByCategory: List<Category>
)
