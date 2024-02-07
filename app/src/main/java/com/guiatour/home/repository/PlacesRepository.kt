package com.guiatour.home.repository

import com.guiatour.home.data.Places
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface PlacesRepository {
    suspend fun fetchPlacesByCategory(category: String): Flow<Places>
}