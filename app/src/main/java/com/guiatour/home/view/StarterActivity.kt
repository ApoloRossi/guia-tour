package com.guiatour.home.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.guiatour.home.view.parallax.ParallaxActivity
import com.guiatour.ui.theme.GuiaTourTheme

class StarterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GuiaTourTheme {
                StarterScreen()
            }
        }
    }

    @Composable
    fun StarterScreen() {
        Column {
            Button(onClick = { startActivity(Intent(this@StarterActivity, HomeActivity::class.java)) }) {
                Text(text = "Init Guia tour App")
            }

            Button(onClick = { startActivity(Intent(this@StarterActivity, ParallaxActivity::class.java)) }) {
                Text(text = "Nested Parallax Sample")
            }
        }

    }

    @Preview
    @Composable
    fun StarterScreenPreview() {
        GuiaTourTheme {
            StarterScreen()
        }
    }
}