package com.guiatour.usecase

import com.guiatour.home.data.Places
import kotlinx.coroutines.flow.Flow

interface PlacesUseCase {
    suspend fun fetchPlacesByCategory(category: String) : Flow<Places>
}