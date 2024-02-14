package com.guiatour.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.guiatour.home.data.Places

@Composable
fun CategoriesList(places: List<Places>) {
    LazyColumn {
        items(places, key = { it.category }) {
            Column {
                CategorySection(it)
            }
        }
    }
}