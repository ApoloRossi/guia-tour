package com.guiatour

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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

    //create a rounded image compose function
    @Composable
    fun RoundedImage() {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "image",
            modifier = Modifier
                .clip(RoundedCornerShape(80.dp))

        )
    }

    @Preview
    @Composable
    fun PlaceDetailContent() {
        val placeName = intent.getStringExtra(PARAM)
        Scaffold(topBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.default_green))

            ) {
                IconButton(onClick = { }) {
                    Icon(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                    )
                }

                Text(
                    text = "São Paulo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    color = Color.White,
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)

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
                    Box(
                        Modifier
                            .padding(top = 80.dp)
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .background(Color.White)
                    )

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


}






