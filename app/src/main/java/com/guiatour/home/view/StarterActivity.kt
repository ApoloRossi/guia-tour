package com.guiatour.home.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
        Button(onClick = { startActivity(Intent(this, MainActivity::class.java)) }) {
            Text(text = "Init Guia tour App")
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