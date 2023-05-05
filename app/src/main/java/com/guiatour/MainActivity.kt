package com.guiatour

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guiatour.ui.theme.GreenApp
import com.guiatour.ui.theme.GuiaTourTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            GuiaTourTheme {
                // A surface container using the 'background' color from the theme

                MainContent()

            }
        }
    }

    @Composable
    @Preview
    private fun MainContent() {
        val onActionClick: () -> Unit = {}

        Scaffold(topBar = {
            TopAppBar()
        }, floatingActionButton = { MainFab() }) {
            //Create a container that uses SearchInputField and HorizontalScrollableListComponentWithNameAndPicture
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(it)
            ) {
                SearchInputField()
                createAnHorizontalScrollableListComponentWithNameAndPicture(
                    listOf(
                        listOf(
                            "Jaraguá", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera"
                        ),
                        listOf("Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera"),
                        listOf("Aspicueta", "Aspicueta", "Ibirapuera", "Ibirapuera", "Ibirapuera")
                    )
                )
            }
        }
    }

    @Composable
    fun createAnHorizontalScrollableListComponentWithNameAndPicture(lists: List<List<String>>) {
        LazyColumn {
            items(lists.size) { index ->
                LazyRow(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    items(lists[index].size) { subListIndex ->
                        createAnHorizontalListComponentWithNameAndPicture(lists[index][subListIndex])
                    }
                }
            }
        }
    }

    @Composable
    fun createAnHorizontalListComponentWithNameAndPicture(name: String) {
        Column(
            modifier = Modifier.padding(8.dp), //.horizontalScroll(rememberScrollState())
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
            },
            placeholder = { Text(text = "Faça a sua busca") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),

            )
    }

    @Composable
    private fun TopAppBar() {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Olá, Apolo", color = GreenApp)
                Text(
                    text = "Guia Tour",
                    fontSize = 30.sp,
                    color = Color.Black,
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)

                )
                Text(
                    text = "Filtrar", color = GreenApp
                )
            }
        }
        /*

        Row(
            Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = "Guia Tour")
            Text(text = "Filtrar")
        }*/
    }

    @Composable
    fun MainFab() {
        FloatingActionButton(onClick = { showDetail() }) {
            Icon(imageVector = Icons.Filled.Call, "")
        }
    }

    private fun showDetail() {
        startActivity(Intent(this, PlaceDetail::class.java))
    }
}



