package com.anshul.collagemaker

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Precision

@Composable
fun ImagePicker(uri: Uri, ){
    val sizePx = with(LocalDensity.current) { 80.dp.roundToPx() }
    Box(modifier = Modifier.aspectRatio(1f)){

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(uri)
                .size(sizePx, sizePx)
                .precision(Precision.INEXACT)
                .crossfade(false)
                .build(),
            contentDescription = null,
            modifier = Modifier


                .padding(2.dp),
            contentScale = ContentScale.Crop
        )
    }

}
