package com.guiatour.home.usecase

import com.guiatour.home.repository.PlacesRepository
import com.guiatour.usecase.PlacesUseCase
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class PlacesUseCaseImpl @Inject constructor(
    private val placesRepository: PlacesRepository
) : PlacesUseCase {
    override suspend fun fetchPlacesByCategory(category: String) = run {
        placesRepository.fetchPlacesByCategory(category).catch {
            throw it
        }
    }
}