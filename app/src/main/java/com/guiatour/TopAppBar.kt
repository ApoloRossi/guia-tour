package com.guiatour

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guiatour.ui.theme.GreenApp

@Composable
fun TopAppBar(
    title: String,
    backButton: Boolean = false,
    filter: Boolean = false,
    color: Color? = null
) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(color = color ?: Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //create an IconButton with the back arrow icon
            if (backButton) {
                IconButton(onClick = { }) {
                    Icon(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                    )
                }
            } else {
                Text(text = "Olá, Apolo", color = GreenApp, modifier = Modifier.align(Alignment.CenterVertically))
            }

            Text(
                text = title,
                fontSize = 30.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
            )

            if (filter) {
                Text(
                    //align the text to the end of the row
                    text = "filter", color = GreenApp
                )
            }

        }
    }
}