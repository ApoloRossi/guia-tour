package com.guiatour.home.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guiatour.home.data.Places
import com.guiatour.usecase.PlacesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val placesUseCase: PlacesUseCase
) : ViewModel() {

    private var homeUIMutable: MutableSharedFlow<HomeUIState> = MutableSharedFlow()
    var homeUI: Flow<HomeUIState> = homeUIMutable

    private var allPlaces = mutableListOf<Places>()

    fun fetchPlaces() {
        viewModelScope.launch {
            homeUIMutable.emit(HomeUIState.Loading)
            fetchParques()
            fetchBares()
            fetchBaladas()
        }
    }

    private fun fetchParques() {
        viewModelScope.launch {
            placesUseCase.fetchPlacesByCategory("Parques").onEach {
                allPlaces.add(it)
            }.map {
                homeUIMutable.emit(HomeUIState.Success(mutableStateOf(allPlaces)))
            }.collect{

            }
        }
    }

    private fun fetchBares() {
        viewModelScope.launch {
            placesUseCase.fetchPlacesByCategory("Bares").onEach {
                allPlaces.add(it)
            }.map {
                homeUIMutable.emit(HomeUIState.Success(mutableStateOf(allPlaces)))
            }.collect {

            }
        }
    }

    private fun fetchBaladas() {
        viewModelScope.launch {
            placesUseCase.fetchPlacesByCategory("Baladas").onEach {
                allPlaces.add(it)
            }.map {
                homeUIMutable.emit(HomeUIState.Success(mutableStateOf(allPlaces)))
            }.collect{

            }
        }
    }

}