package com.guiatour.home.viewModel

import androidx.compose.runtime.MutableState
import com.guiatour.home.data.Places

sealed interface HomeUIState {
    data class Success(val places: MutableState<List<Places>>) : HomeUIState
    data class Error(val errorMessage : Int) : HomeUIState
    object InternetError : HomeUIState
    object Empty : HomeUIState
    object Loading : HomeUIState
}