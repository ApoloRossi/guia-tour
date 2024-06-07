package com.guiatour.home.view.components

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guiatour.extensions.getImageBitmapFromUrl
import com.squareup.picasso.Picasso

@Preview(showBackground = true)
@Composable
fun PlacesListForCategoryPreview() {
    PlacesListForCategory("Avenida Paulista", "", {})
}

@Composable
fun PlacesListForCategory(name: String, imageURL: String, onClick: () -> Unit = {}) {

    var drawable by remember {
        mutableStateOf<ImageBitmap?>(null)
    }

    drawable = imageURL.getImageBitmapFromUrl()

    Column(
        modifier = Modifier
            .padding(end = 8.dp)
            .clickable { onClick.invoke() }
    ) {
        drawable?.let {image ->
            Image(
                bitmap = image,
                contentDescription = "Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }?: kotlin.run {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(ShimmerBrush(true), shape = RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colors.onPrimary
        )
    }

}