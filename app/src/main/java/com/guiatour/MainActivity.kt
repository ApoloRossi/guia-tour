package com.guiatour

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    private fun MainContent() {
        val onActionClick: () -> Unit = {}

        Scaffold(
            topBar = {
                TopAppBar()
            }, floatingActionButton = { MainFab() }
        ) { padding ->
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal,
                        ),
                    ),
            ) {
                Column(Modifier.fillMaxSize()) {
                    /*CenterAlignedTopAppBar(
                                    title = { Text(text = stringResource(id = R.string.app_name)) },
                                    actions = {
                                        IconButton(onClick = onActionClick) {
                                            Icon(
                                                imageVector = Icons.Rounded.Settings,
                                                contentDescription = "",
                                                tint = MaterialTheme.colors.onPrimary,
                                            )
                                        }
                                    },
                                    *//*  colors = colors,
                                          modifier = modifier.testTag("niaTopAppBar"),*//*
                                    )*/
                }

            }
        }
        //Greeting("Android")
    }

    @Composable
    @Preview
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



