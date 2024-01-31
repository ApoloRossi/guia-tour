package com.guiatour.home.usecase

import com.guiatour.home.repository.PlacesRepository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class PlacesUseCaseImpl @Inject constructor(
    private val placesRepository: PlacesRepository
) : PlacesUseCase {
    override suspend fun fetchPlacesByCategory(category: String, coroutineScope : CoroutineScope) = run {
        placesRepository.fetchPlacesByCategory(category, coroutineScope)
    }
}