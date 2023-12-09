package com.guiatour.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guiatour.R
import com.guiatour.ui.theme.GuiaTourTheme


class PrivatePlaceActivity : ComponentActivity() {

    companion object {
        private const val PARAM = "PARAM"
        fun newInstance(context: Context, param: String) =
            Intent(context, PrivatePlaceActivity::class.java).apply {
                putExtra(PARAM, param)
            }
    }

    private val allPlaces = listOf("Clube do minhoca", "Bixiga", "Clube do minhoca", "Bixiga", "Clube do minhoca", "Bixiga")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GuiaTourTheme {
                MainContext()
            }
        }
    }

    @Composable
    private fun MainContext() {
        Column(modifier = Modifier.fillMaxWidth()) {
            createARoundedSearchBarWithText()
            LazyColumn {
                items(allPlaces.size) { index ->
                    createCardWithImageTitleAndDescription(allPlaces[index])
                }
            }
        }
    }


   //Create a rounded text input field
    @Composable
    fun createARoundedSearchBarWithText() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column {
                Text(
                    text = "Pesquisar",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }


   /* @Preview(showBackground = true)
    @Composable
    private fun CustomLinearProgressBar() {
        Column(modifier = Modifier.fillMaxWidth()) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp),
                color = Color.Red //progress color
            )
        }
    }*/

    //create a Bottomsheet with a list of places



    @Composable
    fun createCardWithImageTitleAndDescription(
        title: String
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "descrição: $title",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            }
        }
    }
}




