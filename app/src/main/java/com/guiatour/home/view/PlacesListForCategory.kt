package com.guiatour.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guiatour.R


@Preview(showBackground = true)
@Composable
fun PlacesListForCategoryPreview() {
    PlacesListForCategory("Avenida Paulista", {})
}

@Composable
fun PlacesListForCategory(name: String, onClick: () -> Unit = {}) {
    Surface(
        shape = RoundedCornerShape(16.dp), elevation = 8.dp, modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(0.dp)
                .clickable { onClick.invoke() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.av_paulista),
                contentDescription = "Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(120.dp).clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)).background(Color.Red)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}