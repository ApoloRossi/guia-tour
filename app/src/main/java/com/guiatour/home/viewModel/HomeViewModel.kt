package com.guiatour.home.viewModel

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guiatour.home.usecase.PlacesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val placesUseCase: PlacesUseCase,
    private val connectivityManager: ConnectivityManager
) : ViewModel() {

    private var homeUIMutable: MutableSharedFlow<HomeUIState> = MutableSharedFlow(1)
    var homeUI: SharedFlow<HomeUIState> = homeUIMutable

    fun fetchPlaces() {
        if (hasInternetConnection()) {
            homeUIMutable.tryEmit(HomeUIState.Loading)
            fetchBares()
            fetchParques()
            fetchBaladas()
            fetchMock()
        }
    }

    private fun fetchMock() {
        viewModelScope.launch {
            repeat(8) {
                placesUseCase.fetchPlacesByCategory("Mock$it").collect {
                    homeUIMutable.emit(it)
                }
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

            val hasCapability =
                networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    ?: false

            if (!hasCapability) {
                homeUIMutable.tryEmit(HomeUIState.InternetError)
            }

            return hasCapability
        }
        return true
    }

    private fun fetchParques() {
        viewModelScope.launch {
            placesUseCase.fetchPlacesByCategory("Parques").collect {
                homeUIMutable.emit(it)
            }
        }
    }

    private fun fetchBares() {
        viewModelScope.launch {
            placesUseCase.fetchPlacesByCategory("Bares").collect {
                homeUIMutable.emit(it)
            }
        }
    }

    private fun fetchBaladas() {
        viewModelScope.launch {
            placesUseCase.fetchPlacesByCategory("Baladas").collect {
                homeUIMutable.emit(it)
            }
        }
    }
}