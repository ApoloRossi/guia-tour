package com.guiatour.home.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.guiatour.home.viewModel.HomeViewModel
import com.guiatour.R
import com.guiatour.home.data.Places
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
            GuiaTourTheme(darkTheme = true) {
                MainContent()
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchPlaces()
            }
        }
    }

    //@Preview(showBackground = true)
    @Composable
    private fun MainContent() {

        val homeUIState: HomeUIState by viewModel.homeUI.collectAsState(initial = HomeUIState.Loading)

        Scaffold(topBar = {
            com.guiatour.TopAppBar("Guia Tour", filter = true)
        }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(it)
            ) {
                SearchInputField()

                when(homeUIState) {
                    is HomeUIState.Success -> {
                        CategoriesList((homeUIState as HomeUIState.Success).places.value)
                    }
                    is HomeUIState.Loading -> {
                        LoaderComponent()
                    }
                    is HomeUIState.Empty -> {
                        EmptyComponent()
                    }
                    is HomeUIState.Error -> {
                        ErrorComponent(homeUIState)
                    }
                    is HomeUIState.InternetError -> {
                        InternetErrorComponent()
                    }
                }
            }
        }
    }

    @Composable
    private fun EmptyComponent() {
        Text(
            text = "VAZIO",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(8.dp),
        )
    }

    @Composable
    private fun ErrorComponent(homeUIState: HomeUIState) {
        Text(
            text = (homeUIState as HomeUIState.Error).errorMessage,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(8.dp),
        )
    }

    @Composable
    fun InternetErrorComponent() {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentSize(align = Alignment.Center),
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_no_internet),
                contentDescription = "No internet",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.Center)
            )

            Text(
                text = "Sem conexão com a internet",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onPrimary,
                style = TextStyle(textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth(),
            )

            Button(
                onClick = {
                    viewModel.fetchPlaces()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Tentar novamente")
            }
        }
    }

    @Composable
    fun LoaderComponent() {
        Column(Modifier.fillMaxWidth(1f), Arrangement.Center, Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colors.secondary,
            )
        }
    }

    @Composable
    fun CategoriesList(places: List<Places>) {
        LazyColumn {
            items(places, key = { it.category }) {
                Column {
                    CategorySection(it)
                }
            }
        }
    }

    @Composable
    private fun CategorySection(
        categories: Places
    ) {
        Text(
            text = categories.category,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(8.dp),
        )
        LazyRow(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            items(categories.placesByCategory.size) { place ->
                PlacesListForCategory(categories.placesByCategory[place].name)
            }
        }
    }

    @Composable
    fun PlacesListForCategory(name: String) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .clickable { showDetail() },
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Picture",
                modifier = Modifier.size(110.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }

    @Composable
    private fun SearchInputField() {
        var inputValue = remember { mutableStateOf("") }

        OutlinedTextField(
            value = inputValue.value,
            onValueChange = { newText ->
                inputValue.value = newText
                //copilot: filter the allPlaces by newText
                //allPlaces.filter { it.contains(newText) }
            },
            textStyle = TextStyle(color = MaterialTheme.colors.onPrimary),
            placeholder = { Text(text = "Faça a sua busca") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp)
        )
    }

    private fun showDetail() {

        //startActivity PrivatePlace
        //startActivity(PrivatePlaceActivity.newInstance(this, "Ibirapuera"))
        startActivity(PlaceDetailActivity.newInstance(this, "Ibirapuera"))
    }

}