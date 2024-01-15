package com.guiatour.home.data

import com.google.gson.annotations.SerializedName

data class Places(
    val category : String,
    @SerializedName("places_by_category")
    val placesByCategory : List<Place>
)