package com.guiatour

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.guiatour.ui.theme.GuiaTourTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuiaTourTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val onActionClick: () -> Unit = {}

                    CenterAlignedTopAppBar(
                        title = { Text(text = stringResource(id = R.string.app_name)) },
                        actions = {
                            IconButton(onClick = onActionClick) {
                                Icon(
                                    imageVector = Icons.Rounded.Settings,
                                    contentDescription = "",
                                    tint = MaterialTheme.colors.surface,
                                )
                            }
                        },
                      /*  colors = colors,
                        modifier = modifier.testTag("niaTopAppBar"),*/
                    )
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GuiaTourTheme {
        Greeting("Android")
    }
}