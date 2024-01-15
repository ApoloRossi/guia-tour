package com.guiatour.home.repository.remote

import com.guiatour.home.data.Places
import kotlinx.coroutines.flow.Flow

interface PlacesRemoteDataSource {
    suspend fun fetchPlacesByCategory(category: String): Flow<Places>
}