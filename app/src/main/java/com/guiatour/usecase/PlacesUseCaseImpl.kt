package com.guiatour.usecase

import com.guiatour.data.PlacesRepository
import javax.inject.Inject

class PlacesUseCaseImpl @Inject constructor(
    private val placesRepository: PlacesRepository
) : PlacesUseCase {
    override suspend fun fetchPlacesByCategory(category: String) = run {
        placesRepository.fetchPlacesByCategory(category)
    }
}