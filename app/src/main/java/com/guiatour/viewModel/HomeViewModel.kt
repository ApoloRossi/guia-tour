package com.guiatour.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guiatour.data.Places
import com.guiatour.data.PlacesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val placesRepository: PlacesRepository
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
            placesRepository.fetchPlacesByCategory("Parques").onEach {
                allPlaces.add(it)
            }.collect {
                placesMutableLiveData.postValue(allPlaces)
            }
        }
    }

    private fun fetchBares() {
        viewModelScope.launch {
            placesRepository.fetchPlacesByCategory("Bares").onEach {
                allPlaces.add(it)
            }.collect {
                placesMutableLiveData.postValue(allPlaces)
            }

        }
    }

    private fun fetchBaladas() {
        viewModelScope.launch {
            placesRepository.fetchPlacesByCategory("Baladas").onEach {
                allPlaces.add(it)
            }.collect {
                placesMutableLiveData.postValue(allPlaces)
            }
        }
    }

}