package com.guiatour.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guiatour.viewModel.HomeViewModel
import com.guiatour.R
import com.guiatour.data.Places
import com.guiatour.ui.theme.GuiaTourTheme

class MainActivity : ComponentActivity() {

    private var allPlaces: MutableList<Places> = mutableStateListOf()

    private val viewModel: HomeViewModel by viewModels { HomeViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GuiaTourTheme(darkTheme = true) {
                MainContent()
            }
        }

        viewModel.placesLiveData.observe(this) {
            allPlaces.add(it)
        }

        viewModel.fetchPlaces()

    }

    @Preview(showBackground = true)
    @Composable
    private fun MainContent() {
        val onActionClick: () -> Unit = {}

        Scaffold(topBar = {
            com.guiatour.TopAppBar("Guia Tour", filter = true)
        }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(it)
            ) {
                SearchInputField()

                if (allPlaces.isEmpty()) {
                    LoaderComponent()
                } else {
                    CategoriesList(allPlaces.last())
                }
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
    fun CategoriesList(places: Places) {
        LazyColumn {
            items(1) { index ->
                CategorySection(places)
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