package com.guiatour.home.usecase

import androidx.compose.runtime.mutableStateOf
import com.guiatour.R
import com.guiatour.home.data.Categories
import com.guiatour.home.data.Place
import com.guiatour.home.data.Places
import com.guiatour.home.repository.PlacesRepository
import com.guiatour.home.viewModel.HomeUIState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PlacesUseCaseImpl @Inject constructor(
    private val placesRepository: PlacesRepository
) : PlacesUseCase {

    private var allPlaces = mutableListOf<Places>()

    private fun categoryToPlace(category: Categories): Places {
        return Places(
            category.category,
            category.groupsByCategory.map {
                 Place(it.name, image = it.image)
            }

        )
    }

    override suspend fun fetchMostSeenCategories()  =
        run {
            placesRepository.fetchMostSeenCategories().onEach {
                if (it.category.isNotEmpty()) {
                    allPlaces.add(categoryToPlace(it))
                }
            }.map {
                if (allPlaces.isEmpty()) {
                    HomeUIState.Empty
                } else {
                    HomeUIState.Success(mutableStateOf(allPlaces))
                }
            }.catch {
                emit(HomeUIState.Error(R.string.generic_error_message))
            }
        }
    override suspend fun fetchPlacesByCategory(category: String) =
        run {
            placesRepository.fetchPlacesByCategory(category).onEach {
                if (it.placesByCategory.isNotEmpty()) {
                    allPlaces.add(it)
                }
            }.map {
                if (allPlaces.isEmpty()) {
                    HomeUIState.Empty
                } else {
                    HomeUIState.Success(mutableStateOf(allPlaces))
                }
            }.catch {
                emit(HomeUIState.Error(R.string.generic_error_message))
            }
        }
}