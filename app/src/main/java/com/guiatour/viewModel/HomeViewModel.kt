package com.guiatour.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.guiatour.data.Places
import com.guiatour.data.PlacesRepository
import com.guiatour.data.PlacesRepositoryImpl
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(private val placesRepository: PlacesRepository) : ViewModel() {

    private val placesMutableLiveData: MutableLiveData<MutableList<Places>> = MutableLiveData()
    val placesLiveData: LiveData<MutableList<Places>> = placesMutableLiveData

    private var allPlaces = mutableListOf<Places>()

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                return HomeViewModel(PlacesRepositoryImpl()) as T
            }
        }
    }

    fun fetchPlaces() {
        viewModelScope.launch {
            fetchParques()
            fetchBares()
            fetchBaladas()
        }
    }

    fun fetchParques() {
        viewModelScope.launch {
            placesRepository.fetchPlacesByCategory("Parques").onEach {
                allPlaces.add(it)
            }.collect {
                placesMutableLiveData.postValue(allPlaces)
            }
        }
    }

    fun fetchBares() {
        viewModelScope.launch {
            placesRepository.fetchPlacesByCategory("Bares").onEach {
                allPlaces.add(it)
            }.collect {
                placesMutableLiveData.postValue(allPlaces)
            }

        }
    }

    fun fetchBaladas() {
        viewModelScope.launch {
            placesRepository.fetchPlacesByCategory("Baladas").onEach {
                allPlaces.add(it)
            }.collect {
                placesMutableLiveData.postValue(allPlaces)
            }
        }
    }

}