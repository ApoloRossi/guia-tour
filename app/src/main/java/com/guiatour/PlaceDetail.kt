package com.guiatour

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guiatour.ui.theme.GuiaTourTheme

class PlaceDetail : ComponentActivity() {

    companion object {
        private const val PARAM = "PARAM"
        fun newInstance(context: Context, param: String) =
            Intent(context, PlaceDetail::class.java).apply {
                putExtra(PARAM, param)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GuiaTourTheme {
                PlaceDetailContent()
            }
        }
    }

    @Preview
    @Composable
    fun PlaceDetailContent() {
        //val placeName = intent.getStringExtra(PARAM)
        Scaffold(topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.default_green))

            ) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                    )
                }
                Text(
                    text = "São Paulo",
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color.White,
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

            }

        }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(it)
            ) {
                Box(
                    Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.default_green))
                ) {
                    Column(
                        Modifier
                            .padding(top = 80.dp)
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Text(
                            text = "Parque do Ibirapuera",
                            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                            fontSize = 24.sp,
                            modifier = Modifier
                                .padding(top = 50.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = "Tour em Ibirapuera",
                            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        TabBar()

                    }

                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "image",
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .clip(RoundedCornerShape(60.dp))
                            .align(Alignment.TopCenter)
                    )

                }

            }
        }
    }





    /* Create a composable Tab Bar with two dividers. The first one will call 'Posts' and the second 'Photos'
    * Inside 'Posts' create a red Column layout and for 'Photos' create a blue Column layout.
    */
    @Composable
    fun TabBar() {
        var selectedTab by remember { mutableStateOf(0) }
        Column(modifier = Modifier.padding(top = 24.dp)) {
            TabRow(
                selectedTabIndex = selectedTab,
                backgroundColor = Color.White,
                contentColor = Color.Black,
                divider = {

                }
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Posts") },
                    modifier = Modifier
                        .border(
                            border = BorderStroke(
                                width = 1.dp,
                                color = Color.Black
                            ),
                            shape = RoundedCornerShape(40.dp)
                        )
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Photos") },
                    modifier = Modifier
                        .border(
                            border = BorderStroke(
                                width = 1.dp,
                                color = Color.Black
                            ),
                            shape = RoundedCornerShape(40.dp)
                        )
                )
            }
            when (selectedTab) {
                0 -> {
                    Column(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {

                        LazyColumn {
                            items(3) {
                                //create an image with a title bellow and a description text
                                Column(
                                    modifier = Modifier
                                        .clickable { }
                                        .fillMaxWidth()
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_launcher_background),
                                        contentDescription = "image",
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .width(500.dp)
                                            .height(300.dp)
                                            .clip(RoundedCornerShape(20.dp))

                                    )
                                    Text(
                                        text = "Title",
                                        modifier = Modifier
                                            .padding(top = 16.dp)
                                            .align(Alignment.CenterHorizontally)
                                    )
                                    Text(
                                        text = "Description",
                                        modifier = Modifier
                                            .padding(top = 16.dp)
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }
                            }
                        }
                    }
                }
                1 -> {
                    Column(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {

                    }
                }
            }
        }
    }
}






