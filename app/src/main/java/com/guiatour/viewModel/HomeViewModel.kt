package com.guiatour.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guiatour.data.Places
import com.guiatour.usecase.PlacesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val placesUseCase: PlacesUseCase
) : ViewModel() {

    private val placesMutableLiveData: MutableLiveData<MutableList<Places>> = MutableLiveData()
    val placesLiveData: LiveData<MutableList<Places>> = placesMutableLiveData

    private var allPlaces = mutableListOf<Places>()

    fun fetchPlaces() {
        viewModelScope.launch {
            fetchParques()
            fetchBares()
            fetchBaladas()
        }
    }

    private fun fetchParques() {
        viewModelScope.launch {
            placesUseCase.fetchPlacesByCategory("Parques").onEach {
                allPlaces.add(it)
            }.collect {
                placesMutableLiveData.postValue(allPlaces)
            }
        }
    }

    private fun fetchBares() {
        viewModelScope.launch {
            placesUseCase.fetchPlacesByCategory("Bares").onEach {
                allPlaces.add(it)
            }.collect {
                placesMutableLiveData.postValue(allPlaces)
            }

        }
    }

    private fun fetchBaladas() {
        viewModelScope.launch {
            placesUseCase.fetchPlacesByCategory("Baladas").onEach {
                allPlaces.add(it)
            }.collect {
                placesMutableLiveData.postValue(allPlaces)
            }
        }
    }

}