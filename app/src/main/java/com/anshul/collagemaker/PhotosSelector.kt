package com.anshul.collagemaker

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.anshul.collagemaker.HomeScreen.pressStartFont
import com.anshul.collagemaker.ui.theme.MyCustomGray
import com.anshul.collagemaker.ui.theme.MyCustomWhite

@Composable
fun PhotosSelector(navController: NavHostController, count: Int) {

    val context = LocalContext.current

    var images by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var imagesSelected by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var page by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
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
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = MyCustomGray), verticalArrangement = Arrangement.Bottom) {
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .padding(start = 10.dp, top = 12.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween ) {
                    HorizontalImageList(imagesSelected, modifier = Modifier)
                    Button(onClick = {
                        if(imagesSelected.size==count){
                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set("selectedImages", imagesSelected)

                            navController.navigate("edit")
//
//                            scope.launch {
//                                // Show a loading state if you have many images
//                                val internalUris = copyUrisToInternal(context, imagesSelected)
//
//                                // Convert the List<Uri> to List<String> for the SavedStateHandle
//                                val internalUriStrings = internalUris.map { it.toString() }
//
//                                // 3. Navigate first
//                                navController.navigate("edit")
//
//                                // 4. Set the data on the NEW current backstack entry (the Edit screen)
//                                navController.currentBackStackEntry
//                                    ?.savedStateHandle
//                                    ?.set("selectedImages", internalUriStrings)
//                            }
                        }
                    }) {
                        Text(text = "Next")
                    }
                }
               
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                state = gridState
            ) {
                items(images) { uri ->
                    var isSelected=true
                    if(imagesSelected.contains(uri)){
                        isSelected=true
                    }else{
                        isSelected=false
                    }
                    ImagePicker(uri, click={
                        if(isSelected){
                            imagesSelected=imagesSelected-uri
                        }else{
                            Log.d("anshul", imagesSelected.size.toString())
                            if(imagesSelected.size<count){
                                imagesSelected=imagesSelected+uri
                            }else{
                                Log.d("Anshul","Jyada ho gya AAg")
                            }

                        }
                    }, isSelected)

                }
            }
        }

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

@Composable
fun HorizontalImageList(imageUris: List<Uri>, modifier: Modifier) {
    LazyRow {
        items(imageUris) { uri ->
            Image(
                painter = rememberAsyncImagePainter(model = uri),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}
