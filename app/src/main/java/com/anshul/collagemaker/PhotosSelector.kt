package com.anshul.collagemaker

import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Precision
import coil.size.Size
import com.anshul.collagemaker.HomeScreen.pressStartFont
import com.anshul.collagemaker.ui.theme.MyCustomGray
import com.anshul.collagemaker.ui.theme.MyCustomWhite

@Composable
@Preview(showSystemUi = true)
fun PhotosSelector(){

    val context = LocalContext.current

    var images by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var page by remember { mutableStateOf(0) }

    val pageSize = 30
    val gridState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        images = loadImages(context, page, pageSize)
    }

    LaunchedEffect(gridState) {

        snapshotFlow {
            gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastVisibleIndex ->

            if (lastVisibleIndex != null &&
                lastVisibleIndex >= images.size - 10 // 👈 threshold
            ) {
                page++

                val newImages = loadImages(context, page, pageSize)

                images = images + newImages
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize().background(color = MyCustomGray)) {
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .padding(start = 10.dp, top = 12.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "CHOOSE PHOTOS",
                fontFamily = pressStartFont,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = MyCustomWhite
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            state = gridState
        ) {
            items(images) { uri ->

                // adjust to your grid cell


            }
        }

    }

}



fun loadImages(
    context: Context,
    page:Int,
    pageSize: Int
): List<Uri>{
    val images =mutableListOf<Uri>()
    val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

    val sortOrder
    ="${MediaStore.Images.Media.DATE_ADDED} DESC"
    val cursor=context.contentResolver.query(
        collection,
        arrayOf(MediaStore.Images.Media._ID),
        null,
        null, sortOrder
    )

    cursor?.use {
        val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

        // 👇 Skip previous items
        val startIndex = page * pageSize
        if (it.moveToPosition(startIndex)) {

            var count = 0

            do {
                val id = it.getLong(idColumn)
                val uri = ContentUris.withAppendedId(collection, id)
                images.add(uri)

                count++
            } while (it.moveToNext() && count < pageSize)
        }
    }

    return images

}
