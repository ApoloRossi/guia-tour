package com.guiatour.home.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun SearchInputField() {
    var inputValue = remember { mutableStateOf("") }

    OutlinedTextField(
        value = inputValue.value,
        onValueChange = { newText ->
            inputValue.value = newText
            //copilot: filter the allPlaces by newText
            //allPlaces.filter { it.contains(newText) }
        },
        textStyle = TextStyle(color = MaterialTheme.colors.onPrimary),
        placeholder = { Text(text = "Faça a sua busca") },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    )
}