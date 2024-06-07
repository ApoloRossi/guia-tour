package com.guiatour.home.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import com.guiatour.home.data.Place
import com.guiatour.home.data.Places


fun LazyListScope.categoriesList(places: List<Places>, onItemClicked: (Place) -> Unit) {
    items(places, key = { it.category }) {
        Column {
            CategorySection(it, onItemClicked)
        }
    }
}