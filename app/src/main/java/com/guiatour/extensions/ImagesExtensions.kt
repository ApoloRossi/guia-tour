package com.guiatour.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.squareup.picasso.Picasso

fun String.getImageBitmapFromUrl(): ImageBitmap? {

    var loadedBitmap: ImageBitmap? = null
    val picasso = Picasso.get()

    val target = (object : com.squareup.picasso.Target {

        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            loadedBitmap = bitmap?.asImageBitmap()
            // You can use this bitmap to load image in image view
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            // You can use this bitmap to load image in image view
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

        }
    })

    picasso.load(this).into(target)

    return loadedBitmap
}