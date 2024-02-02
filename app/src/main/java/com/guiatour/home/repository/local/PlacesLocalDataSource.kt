package com.guiatour.home.repository.local

import com.guiatour.home.data.Places
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow

interface PlacesLocalDataSource {
    suspend fun getPlaceFromLocal(
        category: String,
        coroutineScope: CoroutineScope
    ): SharedFlow<Places>? = null

    suspend fun savePlacesToLocal(places: Places)
}