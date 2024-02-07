package com.guiatour.home.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guiatour.home.data.Places
import com.guiatour.home.usecase.PlacesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val placesUseCase: PlacesUseCase
) : ViewModel() {

    private var homeUIMutable: MutableSharedFlow<HomeUIState> = MutableSharedFlow()
    var homeUI: SharedFlow<HomeUIState> = homeUIMutable

    fun fetchPlaces() {
        fetchBares()
        fetchParques()
        fetchBaladas()
    }

    private fun fetchParques() {
        viewModelScope.launch {
            placesUseCase.fetchPlacesByCategory("Parques", this).collect {
                homeUIMutable.emit(it)
            }
        }
    }

    private fun fetchBares() {
        viewModelScope.launch {
            placesUseCase.fetchPlacesByCategory("Bares", this).collect {
                homeUIMutable.emit(it)
            }
        }
    }

    private fun fetchBaladas() {
        viewModelScope.launch {
            placesUseCase.fetchPlacesByCategory("Baladas", this).collect {
                homeUIMutable.emit(it)
            }
        }
    }
}