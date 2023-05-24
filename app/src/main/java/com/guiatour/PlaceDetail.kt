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

                       CraneTabBar(
                            modifier = Modifier
                                .wrapContentWidth()
                                .sizeIn(maxWidth = 500.dp),
                            onMenuClicked = {}
                        ) {
                            CraneTabs(Modifier.fillMaxWidth(), listOf("btn1", "btn2"), CraneScreen.Fly) {

                            }
                        }
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

    @Composable
    fun CraneTabBar(
        modifier: Modifier = Modifier,
        onMenuClicked: () -> Unit,
        children: @Composable (Modifier) -> Unit
    ) {
        Row(modifier) {
            // Separate Row as the children shouldn't have the padding
            Row(Modifier.padding(top = 8.dp)) {

            }
            children(
                Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
        }
    }

  @Composable
    fun CraneTabs(
        modifier: Modifier = Modifier,
        titles: List<String>,
        tabSelected: CraneScreen,
        onTabSelected: (CraneScreen) -> Unit
    ) {
        TabRow(
            selectedTabIndex = tabSelected.ordinal,
            modifier = modifier,
            contentColor = MaterialTheme.colors.onSurface,
            indicator = { tabPositions: List<TabPosition> ->
                Box(
                    Modifier.tabIndicatorOffset(tabPositions[tabSelected.ordinal])
                        .fillMaxSize()
                        .padding(horizontal = 4.dp)
                        .border(BorderStroke(2.dp, Color.White), RoundedCornerShape(16.dp))
                )
            },
            divider = { }
        ) {
            titles.forEachIndexed { index, title ->
                val selected = index == tabSelected.ordinal

                val textModifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)

                Tab(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    selected = selected,
                    onClick = {
                        onTabSelected(CraneScreen.values()[index])
                    }
                ) {
                    Text(
                        modifier = textModifier,
                        text = title.uppercase()
                    )
                }
            }
        }
    }

    enum class CraneScreen {
        Fly, Sleep, Eat
    }


}






