package com.anshul.collagemaker.HomeScreen

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.anshul.collagemaker.HomeScreen.Gridlayout.FiveGridlayout
import com.anshul.collagemaker.HomeScreen.Gridlayout.FourGridLayout
import com.anshul.collagemaker.HomeScreen.Gridlayout.Threegridlayout
import com.anshul.collagemaker.HomeScreen.Gridlayout.TwogridLayout
import com.anshul.collagemaker.R
import com.anshul.collagemaker.ui.theme.MyCustomGray
import com.anshul.collagemaker.ui.theme.MyCustomWhite

val pressStartFont = FontFamily(
    Font(R.font.pressstart2p)
)


data class FontItem(
    val font: FontFamily,
    val name: String
) {

}
@Composable
@Preview(showSystemUi = true)

fun EditScreen(navController: NavHostController?=null) {

    var radiusSeekbar by remember { mutableStateOf(23f) }
    var gapSeekbar by remember { mutableStateOf(8f) }
    val coroutineScope = rememberCoroutineScope()
    val graphicsLayer = rememberGraphicsLayer()
    val textItems = remember {
        mutableStateListOf<TextItem>()
    }

    var selectedId by remember {
        mutableStateOf<Int?>(null)
    }
    val fonts = listOf(
        FontItem(pressStartFont, "Press Start"),
        FontItem(FontFamily.SansSerif, "Sans Serif"),
        FontItem(FontFamily.Serif, "Serif"),
        FontItem(FontFamily.Monospace, "MonoSpace"),
        FontItem(FontFamily.Cursive, "Cursive"),

        )
    val colors = listOf(
        Color.Red, Color.Green, Color.Blue,
        Color.Yellow, Color.Cyan, Color.Magenta, Color.White, Color.Black, Color.LightGray,
        Color.DarkGray, Color.Transparent
    )
    var selectedColor by remember { mutableStateOf(Color.Red) }
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val images = navController
        ?.previousBackStackEntry
        ?.savedStateHandle
        ?.get<List<Uri>>("selectedImages") ?: emptyList()
    Log.d("anshul",images.size.toString())

    Scaffold( containerColor = Color(0xFF2A2A2A)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .verticalScroll(scrollState)

        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "GALLERY",
                        fontFamily = pressStartFont,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = MyCustomGray
                    )
                    Image(imageVector = Icons.Rounded.Home, contentDescription = "")

                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "CUSTOM EDITOR",
                        fontFamily = pressStartFont,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = MyCustomWhite
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .pointerInput(Unit) {
                            detectTapGestures(onTap = { selectedId = null })
                        }
                        .padding(10.dp)
                        .clipToBounds()) {


                    if(images.size ==5){
                        FiveGridlayout(
                            universalRadius =radiusSeekbar.dp,
                            universalGap = gapSeekbar.dp,
                            images=images,
                            labelToBeShown = false,
                            onClicking = {},
                            backgroundColor = selectedColor
                        )
                    }else if(images.size==4){
                        FourGridLayout(
                            universalRadius =radiusSeekbar.dp,
                            universalGap = gapSeekbar.dp,
                            labelToBeShown = false,
                            onClicking = {},
                            images = images,
                            backgroundColor = selectedColor
                        )
                    }else if(images.size==3){
                        Threegridlayout(
                            universalRadius =radiusSeekbar.dp,
                            universalGap = gapSeekbar.dp,
                            labelToBeShown = false,
                            onClicking = {},
                            images = images,
                            backgroundColor = selectedColor
                        )
                    }else if(images.size==2){
                        TwogridLayout(
                            universalRadius =radiusSeekbar.dp,
                            universalGap = gapSeekbar.dp,
                            labelToBeShown = false,
                            onClicking = {},
                            images = images,
                            backgroundColor = selectedColor
                        )
                    }
                    textItems.forEach { item ->
                        EditableTextItem(
                            item = item,
                            isSelected = item.id == selectedId,
                            onSelect = {
                                Toast.makeText(
                                    context,
                                    "This is a Compose Toast!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                selectedId = item.id
                            }
                        )
                    }
                }



                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    items(colors) { item ->
                        Box(modifier = Modifier
                            .size(40.dp)
                            .background(item)
                            .clip(CircleShape)
                            .clickable {
                                selectedColor = item
                            }) {
                        }
                    }
                }


                if (selectedId != null) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White)

                            .padding(start = 20.dp, top = 6.dp, bottom = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,

                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Text Edit",
                            fontFamily = pressStartFont,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 12.sp,
                            color = MyCustomGray
                        )
                    }

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(colors) { item ->
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(item)
                                    .clip(CircleShape)
                                    .clickable {
                                        val itemToUpdate = textItems.find { it.id == selectedId }
                                        itemToUpdate?.color = item
                                    }) {


                            }
                        }
                    }
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),

                        ) {
                        items(fonts) { item ->
                            Box(
                                modifier = Modifier
                                    .border(
                                        width = 2.dp,                      // 1. Thickness of the border
                                        color = Color.White,                // 2. Color of the border
                                        shape = RoundedCornerShape(16.dp)  // 3. The radius of the corners
                                    )
                                    .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
                                    .clickable {

                                        val itemToUpdate = textItems.find { it.id == selectedId }
                                        itemToUpdate?.fontFamily = item.font
                                    }) {
                                Text(text = item.name, color = Color.White, fontFamily = item.font)
                            }
                        }
                    }
                }

                Button(onClick = {
                    textItems.add(
                        TextItem(
                            id = textItems.size
                        )
                    )
                }) {
                    Text(text = "Add Text")
                }
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)

                        .padding(start = 20.dp, top = 6.dp, bottom = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "RADIUS : 50",
                        fontFamily = pressStartFont,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp,
                        color = MyCustomGray
                    )
                }
                Slider(
                    value = radiusSeekbar,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.White,          // 👈 this makes dot white
                        activeTrackColor = Color.Blue,
                        inactiveTrackColor = Color.LightGray
                    ),
                    onValueChange = { newValue ->
                        radiusSeekbar = newValue
                    },
                    valueRange = 0f..100f
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)

                        .padding(start = 20.dp, top = 6.dp, bottom = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "GAP : 50",
                        fontFamily = pressStartFont,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp,
                        color = MyCustomGray
                    )
                }
                Slider(
                    value = gapSeekbar,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.White,
                        activeTrackColor = Color.Blue,
                        inactiveTrackColor = Color.LightGray
                    ),
                    onValueChange = { newValue ->
                        gapSeekbar = newValue
                    },
                    valueRange = 0f..100f
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MyCustomWhite,
                        shape = RoundedCornerShape(16.dp)
                    )) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(modifier = Modifier
                        .weight(1f)
                        .padding(6.dp)){
                        Button(onClick = {
//                            coroutineScope.launch {
//                                val imageBitmap = graphicsLayer.toImageBitmap()
//                                val androidBitmap = imageBitmap.asAndroidBitmap()
//                                saveBitmapToGallery(context, androidBitmap)
//                            }


                        }, modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MyCustomGray
                            ),) {
                            Text(text = "Save", fontSize = 15.sp, fontFamily = pressStartFont,
                                fontWeight = FontWeight.Normal,)
                        }
                    }
                    Box(modifier = Modifier
                        .weight(1f)
                        .padding(6.dp), ){
                        Button(onClick = {}, modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MyCustomGray
                            )) {
                            Text(text = "Export", fontSize = 15.sp, fontFamily = pressStartFont,
                                fontWeight = FontWeight.ExtraBold,)
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }


}

@Composable
fun EditableTextItem(
    item: TextItem,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Box(
        modifier = Modifier
            .offset { IntOffset(item.offsetX.toInt(), item.offsetY.toInt()) }
            .graphicsLayer {
                scaleX = item.scale
                scaleY = item.scale
                rotationZ = item.rotation
            }
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, rotation ->
                    item.offsetX += pan.x
                    item.offsetY += pan.y
                    item.scale *= zoom
                    item.rotation += rotation
                }
            }
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) Color.Cyan else Color.Transparent,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(8.dp)
    ) {
        BasicTextField(
            value = item.text,
            onValueChange = { item.text = it },
            textStyle = TextStyle(
                fontSize = 28.sp,
                color = item.color,
                fontFamily = item.fontFamily
            ),
            modifier = Modifier
                .width(IntrinsicSize.Min)
                // Fix: Use Focus detection to trigger selection
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        onSelect()
                    }
                }
        )
    }
}

class TextItem(
    val id: Int,
    initialText: String = "Hello",
    initialOffsetX: Float = 0f,
    initialOffsetY: Float = 0f,
    initialScale: Float = 1f,
    initialRotation: Float = 0f,
    initialColor: Color = Color.Black,
    initialFontFamily: FontFamily = FontFamily.Default
) {
    var text by mutableStateOf(initialText)
    var offsetX by mutableStateOf(initialOffsetX)
    var offsetY by mutableStateOf(initialOffsetY)
    var scale by mutableStateOf(initialScale)
    var rotation by mutableStateOf(initialRotation)
    var color by mutableStateOf(initialColor)
    var fontFamily by mutableStateOf(initialFontFamily)
}