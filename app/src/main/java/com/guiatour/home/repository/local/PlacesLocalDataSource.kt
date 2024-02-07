package com.guiatour.home.repository.local

import com.guiatour.home.data.Places
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface PlacesLocalDataSource {
    suspend fun getPlaceFromLocal(
        category: String
    ): Flow<Places>? = null

    suspend fun savePlacesToLocal(places: Places)
}