package com.guiatour.data

import kotlinx.coroutines.flow.Flow

interface PlacesRepository {
    suspend fun fetchPlacesByCategory(category: String): Flow<Places>
}