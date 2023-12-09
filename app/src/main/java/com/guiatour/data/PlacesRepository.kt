package com.guiatour.data

interface PlacesRepository {
    suspend fun fetchPlaces(): List<List<String>>
}