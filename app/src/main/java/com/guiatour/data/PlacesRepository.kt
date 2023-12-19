package com.guiatour.data

interface PlacesRepository {
    suspend fun fetchPlacesByCategory(category: String): PlacesResponse
}