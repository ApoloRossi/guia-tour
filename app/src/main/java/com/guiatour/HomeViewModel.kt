package com.guiatour

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    companion object {

        const val HOME_VIEW_MODEL = "HOME_VIEW_MODEL"

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                //val application = checkNotNull(extras[HOME_VIEW_MODEL])

                val savedStateHandle = extras.createSavedStateHandle()

                return HomeViewModel() as T

            }


        }
    }

    private val allPlaces = listOf(
        listOf("JaraguáXXX", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera"),
        listOf("Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera"),
        listOf("AspicuetaY", "Aspicueta", "Ibirapuera", "Ibirapuera", "Ibirapuera")
    )

    fun fetchPlaces(): List<List<String>> {
       /* viewModelScope.launch {
            delay(2000)
        }*/

        return allPlaces
    }

}