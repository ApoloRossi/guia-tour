package com.guiatour.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import com.guiatour.home.data.Places


fun LazyListScope.categoriesList(places: List<Places>) {
    items(places, key = { it.category }) {
        Column {
            CategorySection(it)
        }
    }
}