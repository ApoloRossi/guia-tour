package com.guiatour.home.view.parallax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guiatour.ui.theme.GuiaTourTheme

class ParallaxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GuiaTourTheme(false) {
                ParallaxComponent()
            }
    }
}}

@Preview
@Composable
fun ParallaxComponent() {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(modifier = Modifier) {

            // LazyRow still able to be use, since it has different orientation with parent LazyColumn
            item {
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(listOf("A", "B", "A", "B", "A", "B", "A", "B", "A", "B", "A", "B").size) { story ->
                        Text(text = "Story $story")
                    }
                }
            }

            items(listOf(1,2,3,4,5,6,1,2,3,4,5,6,1,2,3,4,5,6,1,2,3,4,5,6).size) { post ->
                Text(text = "Post $post", modifier = Modifier.height(150.dp))
            }
        }
    }
}