package com.anshul.collagemaker

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.anshul.collagemaker.HomeScreen.EditableTextItem
import com.anshul.collagemaker.HomeScreen.FontItem
import com.anshul.collagemaker.HomeScreen.TextItem
import com.anshul.collagemaker.HomeScreen.pressStartFont
import com.anshul.collagemaker.ui.theme.MyCustomGray
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

data class FontItem(
    val font: FontFamily,
    val name: String
) {

}

@Composable
@Preview(showSystemUi = true)
fun FreeHandCompose(){

    val colors = listOf(
        Color.Red, Color.Green, Color.Blue,
        Color.Yellow, Color.Cyan, Color.Magenta, Color.White, Color.Black, Color.LightGray,
        Color.DarkGray, Color.Transparent
    )
    var selectedColor by remember { mutableStateOf(Color.Red) }
    var selectedId by remember {
        mutableStateOf<Int?>(null)
    }
    val textItems = remember {
        mutableStateListOf<TextItem>()
    }
    val fonts = listOf(
        FontItem(pressStartFont, "Press Start"),
        FontItem(FontFamily.SansSerif, "Sans Serif"),
        FontItem(FontFamily.Serif, "Serif"),
        FontItem(FontFamily.Monospace, "MonoSpace"),
        FontItem(FontFamily.Cursive, "Cursive")
        )
    val items = remember { mutableStateListOf<CanvasItem>() }
    val graphicsLayer = rememberGraphicsLayer()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {

            items.add(
                CanvasItem(
                    id = items.size,
                    uri = it,
                    offset = Offset(200f, 200f),
                    radius = 0.dp,
                    isSelected = false
                )
            )
        }
    }
    Scaffold(
        containerColor = Color(0xFF2A2A2A),
        floatingActionButton = {
            Box(modifier = Modifier

            ) {
                IconButton(
                    onClick = {
                        launcher.launch("image/*")
                    },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)

                        .background(Color.LightGray)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",

                        )
                }
            }
        }

    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Box(modifier = Modifier.background(Color(0xFF2A2A2A))) {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                ){

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
                            text = "Compose",
                            fontFamily = pressStartFont,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            color = MyCustomGray
                        )

                    }

                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp))
                    Text(
                        text = "Please click on Add Button to add images",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp))
                    Box(Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .drawWithContent {
                            graphicsLayer.record {
                                this@drawWithContent.drawContent()
                            }
                            drawLayer(graphicsLayer)
                        }
                        .background(selectedColor)
                        .clipToBounds()) {
                        items.forEach { item ->
                            TransformImage(item,{

                            })
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
                        contentPadding = PaddingValues(16.dp)
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
                                            val itemToUpdate =
                                                textItems.find { it.id == selectedId }
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
                                            width = 2.dp,
                                            color = Color.White,
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .padding(
                                            start = 12.dp,
                                            end = 12.dp,
                                            top = 8.dp,
                                            bottom = 8.dp
                                        )
                                        .clickable {

                                            val itemToUpdate =
                                                textItems.find { it.id == selectedId }
                                            itemToUpdate?.fontFamily = item.font
                                        }) {
                                    Text(
                                        text = item.name,
                                        color = Color.White,
                                        fontFamily = item.font
                                    )
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


                }






            }

            Button(onClick = {
                coroutineScope.launch {
                    val imageBitmap = graphicsLayer.toImageBitmap()

                    val androidBitmap = imageBitmap.asAndroidBitmap()

                    saveBitmapToGallery(context, androidBitmap)
                }
            }, modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(start = 15.dp, end = 70.dp, bottom = 15.dp,)) {
                Text(text = "Export")
            }
        }
    }




}
@Composable
fun TransformImage(
    item: CanvasItem,
    onClick: () -> Unit = {}
) {
    AsyncImage(
        model = item.uri,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(200.dp)
            .offset {
                IntOffset(item.offset.x.roundToInt(), item.offset.y.roundToInt())
            }
            .graphicsLayer {
                scaleX = item.scale
                scaleY = item.scale
                rotationZ = item.rotation
            }
            .clip(RoundedCornerShape(item.radius))
            .pointerInput(item.id) {
                detectTapGestures(onTap = { onClick() })
            }
            .pointerInput(item.id, "transform") {
                detectTransformGestures { _, pan, zoom, rotate ->
                    item.offset = Offset(item.offset.x + pan.x, item.offset.y + pan.y)
                    item.scale *= zoom
                    item.rotation += rotate
                }
            }
    )
}
class CanvasItem(
    val id: Int,
    val uri: Uri,
    isSelected: Boolean = false,
    scale: Float = 1f,
    rotation: Float = 0f,
    offset: Offset = Offset.Zero,
    radius: Dp = 0.dp
) {
    var isSelected by mutableStateOf(isSelected)
    var scale by mutableStateOf(scale)
    var rotation by mutableStateOf(rotation)
    var offset by mutableStateOf(offset)
    var radius by mutableStateOf(radius)
}
