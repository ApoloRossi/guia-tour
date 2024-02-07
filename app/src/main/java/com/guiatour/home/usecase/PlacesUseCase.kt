package com.guiatour.home.usecase

import com.guiatour.home.viewModel.HomeUIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface PlacesUseCase {
    suspend fun fetchPlacesByCategory(category: String, coroutineScope: CoroutineScope) : Flow<HomeUIState>
}