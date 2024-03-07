package com.guiatour.home.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.guiatour.home.data.Place
import com.guiatour.home.viewModel.HomeViewModel
import com.guiatour.home.viewModel.HomeUIState
import com.guiatour.ui.theme.GuiaTourTheme
import com.guiatour.view.PlaceDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GuiaTourTheme(darkTheme = false) {
                MainContent()
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchPlaces()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun MainContent() {

        val homeUIState: HomeUIState by viewModel.homeUI.collectAsState(initial = HomeUIState.Loading)

        val snackBarHostState = remember { SnackbarHostState() }

        Scaffold(topBar = {
            com.guiatour.TopAppBar("Guia Tour", filter = true)
        }, snackbarHost = { SnackbarHost(hostState = snackBarHostState) }) {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(it)
            ) {
                item {
                    SearchInputField()
                }

                when (homeUIState) {
                    is HomeUIState.Success -> {
                        categoriesList((homeUIState as HomeUIState.Success).places.value) { place ->
                            showDetail(place)
                        }
                    }

                    is HomeUIState.Loading -> {
                        item {
                            LoaderComponent()
                        }
                    }

                    is HomeUIState.Empty -> {
                        item {
                            EmptyComponent()
                        }
                    }

                    is HomeUIState.Error -> {
                        item {
                            ErrorComponent(homeUIState, ::fetchPlaces)
                            SnackBarComponent(snackBarHostState, ::fetchPlaces)
                        }
                    }

                    is HomeUIState.InternetError -> {
                        item {
                            InternetErrorComponent(::fetchPlaces)
                        }
                    }
                }
            }
        }
    }

    private fun fetchPlaces() = viewModel.fetchPlaces()

    private fun showDetail(place: Place) {
        //startActivity PrivatePlace
        //startActivity(PrivatePlaceActivity.newInstance(this, "Ibirapuera"))
        startActivity(PlaceDetailActivity.newInstance(this, place.name))
    }

}