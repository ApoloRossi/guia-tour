package com.guiatour.home.usecase

import com.guiatour.home.data.Places
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow

interface PlacesUseCase {
    suspend fun fetchPlacesByCategory(category: String, coroutineScope: CoroutineScope) : SharedFlow<Places>
}