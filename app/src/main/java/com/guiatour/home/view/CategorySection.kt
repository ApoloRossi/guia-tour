package com.guiatour.home.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.guiatour.home.data.Place
import com.guiatour.home.data.Places

@Composable
fun CategorySection(
    categories: Places,
    onItemClicked: (Place) -> Unit
) {
    Text(
        text = categories.category,
        style = MaterialTheme.typography.h5,
        color = MaterialTheme.colors.onPrimary,
        modifier = Modifier.padding(8.dp),
    )
    LazyRow(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        items(categories.placesByCategory.size) { place ->
            PlacesListForCategory(categories.placesByCategory[place].name, categories.placesByCategory[place].image) {
                onItemClicked(categories.placesByCategory[place])
            }
        }
    }
}