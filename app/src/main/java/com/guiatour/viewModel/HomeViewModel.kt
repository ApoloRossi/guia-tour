package com.guiatour.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.guiatour.data.PlacesRepository
import com.guiatour.data.PlacesRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel(private val placesRepository: PlacesRepository) : ViewModel() {

    private val placesMutableLiveData : MutableLiveData<List<List<String>>> = MutableLiveData()
    val placesLiveData : LiveData<List<List<String>>> = placesMutableLiveData

    companion object {

        const val HOME_VIEW_MODEL = "HOME_VIEW_MODEL"

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val placesRepository: PlacesRepository = PlacesRepositoryImpl()
                val savedStateHandle = extras.createSavedStateHandle()

                return HomeViewModel(placesRepository) as T

            }
        }
    }

    fun fetchPlaces() {
        viewModelScope.launch {
            val places = placesRepository.fetchPlaces()
            placesMutableLiveData.postValue(places)
        }
    }

}