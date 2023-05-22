package com.guiatour

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guiatour.ui.theme.GreenApp
import com.guiatour.ui.theme.GuiaTourTheme

class MainActivity : ComponentActivity() {


    private val allPlaces = listOf(
    listOf("JaraguáXXX", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera"),
    listOf("Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera"),
    listOf("AspicuetaY", "Aspicueta", "Ibirapuera", "Ibirapuera", "Ibirapuera")
    )

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GuiaTourTheme {
                MainContent()
            }
        }
    }

    @Composable
    @Preview
    private fun MainContent() {
        val onActionClick: () -> Unit = {}

        Scaffold(topBar = {
            TopAppBar("Guia Tour", filter = true)
        }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(it)
            ) {
                SearchInputField()
                generateCategoriesList(allPlaces)
            }
        }
    }

    @Composable
    fun generateCategoriesList(lists: List<List<String>>) {
        LazyColumn {
            items(lists.size) { index ->
                createCategorySection(lists, index)
            }
        }
    }

    @Composable
    private fun createCategorySection(
        categories: List<List<String>>,
        index: Int
    ) {
        Text(
            text = "Parques",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )
        LazyRow(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            items(categories[index].size) { place ->
                createPlacesListForCategory(categories[index][place])
            }
        }
    }

    @Composable
    fun createPlacesListForCategory(name: String) {
        Column(
            modifier = Modifier.padding(8.dp).clickable { showDetail() },
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Picture",
                modifier = Modifier.size(110.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = name, fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Black
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
                allPlaces.filter { it.contains(newText) }

            },
            placeholder = { Text(text = "Faça a sua busca") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),

            )
    }

    private fun showDetail() {
        startActivity(PlaceDetail.newInstance(this, "Ibirapuera"))
    }
}