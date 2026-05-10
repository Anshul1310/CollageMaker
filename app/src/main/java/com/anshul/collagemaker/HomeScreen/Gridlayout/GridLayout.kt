package com.anshul.collagemaker.HomeScreen.Gridlayout

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.anshul.collagemaker.HomeScreen.pressStartFont
import com.anshul.collagemaker.ui.theme.MyCustomGray

@Composable
@Preview(showSystemUi = true)
fun GridLayout() {
    val universalRadius = 30.dp
    val universalGap = 5.dp
}

@Composable
fun ApniImageChane(uri: Uri, modifier: Modifier = Modifier) {
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = modifier
            .clipToBounds()
            .pointerInput(Unit) {

                detectTapGestures(

                    onDoubleTap = {

                        scale = 1f
                        rotation = 0f
                        offset = Offset.Zero
                    }
                )
            }
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, rotate ->
                    scale *= zoom
                    rotation += rotate
                    offset += pan
                }
            }
    ) {
        AsyncImage(
            model = uri,
            contentDescription = null,
            contentScale = ContentScale.Crop, // Fill the cell properly
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    rotationZ = rotation,
                    translationX = offset.x,
                    translationY = offset.y
                )
        )
    }
}
// -----------------------------------

@Composable
fun Threegridlayout(
    modifier: Modifier = Modifier,
    universalRadius: Dp,
    universalGap: Dp,
    labelToBeShown: Boolean = true,
    onClicking: () -> Unit,
    images: List<Uri> = emptyList(),
    backgroundColor: Color = MyCustomGray
) {

    Box(modifier = modifier
        .fillMaxWidth()
        .clickable {
            onClicking()
        }
        .background(backgroundColor)) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (labelToBeShown) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "3 GRID",
                        fontFamily = pressStartFont,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp,
                        color = MyCustomGray
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp)) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(
                            RoundedCornerShape(
                                topStart = universalRadius,
                                bottomStart = universalRadius
                            )
                        )
                        .background(Color.White)
                ) {
                    images.getOrNull(0)?.let { ApniImageChane(uri = it, modifier = Modifier.fillMaxSize()) }
                }
                Spacer(modifier = Modifier.width(universalGap))
                Column(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topEnd = universalRadius))
                            .background(color = Color.White)
                    ) {
                        images.getOrNull(1)?.let { ApniImageChane(uri = it, modifier = Modifier.fillMaxSize()) }
                    }
                    Spacer(modifier = Modifier.height(universalGap))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomEnd = universalRadius))
                            .background(color = Color.White)
                    ) {
                        images.getOrNull(2)?.let { ApniImageChane(uri = it, modifier = Modifier.fillMaxSize()) }
                    }
                }
            }
        }
    }
}

@Composable
fun TwogridLayout(
    modifier: Modifier = Modifier,
    universalRadius: Dp,
    universalGap: Dp,
    labelToBeShown: Boolean = true,
    onClicking: () -> Unit,
    images: List<Uri> = emptyList(),
    backgroundColor: Color = MyCustomGray
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .clickable {
            onClicking()
        }
        .background(backgroundColor)) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (labelToBeShown) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "2 GRID",
                        fontFamily = pressStartFont,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp,
                        color = MyCustomGray
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp)) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(
                            RoundedCornerShape(
                                topStart = universalRadius,
                                bottomStart = universalRadius
                            )
                        )
                        .background(Color.White)
                ) {
                    images.getOrNull(0)?.let { ApniImageChane(uri = it, modifier = Modifier.fillMaxSize()) }
                }

                Spacer(modifier = Modifier.width(universalGap))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(
                            RoundedCornerShape(
                                topEnd = universalRadius,
                                bottomEnd = universalRadius
                            )
                        )
                        .background(Color.White)
                ) {
                    images.getOrNull(1)?.let { ApniImageChane(uri = it, modifier = Modifier.fillMaxSize()) }
                }
            }
        }
    }
}

@Composable
fun FourGridLayout(
    modifier: Modifier = Modifier,
    universalRadius: Dp,
    universalGap: Dp,
    labelToBeShown: Boolean = true,
    onClicking: () -> Unit,
    images: List<Uri> = emptyList(),
    backgroundColor: Color = MyCustomGray
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .clickable {
            onClicking()
        }
        .background(backgroundColor)) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (labelToBeShown) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "4 GRID",
                        fontFamily = pressStartFont,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp,
                        color = MyCustomGray
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp)) {
                Column(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topStart = universalRadius))
                            .background(color = Color.White)
                    ) {
                        images.getOrNull(0)?.let { ApniImageChane(uri = it, modifier = Modifier.fillMaxSize()) }
                    }
                    Spacer(modifier = Modifier.height(universalGap))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomStart = universalRadius))
                            .background(color = Color.White)
                    ) {
                        images.getOrNull(1)?.let { ApniImageChane(uri = it, modifier = Modifier.fillMaxSize()) }
                    }
                }
                Spacer(modifier = Modifier.width(universalGap))
                Column(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topEnd = universalRadius))
                            .background(color = Color.White)
                    ) {
                        images.getOrNull(2)?.let { ApniImageChane(uri = it, modifier = Modifier.fillMaxSize()) }
                    }
                    Spacer(modifier = Modifier.height(universalGap))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomEnd = universalRadius))
                            .background(color = Color.White)
                    ) {
                        images.getOrNull(3)?.let { ApniImageChane(uri = it, modifier = Modifier.fillMaxSize()) }
                    }
                }
            }
        }
    }
}

@Composable
fun FiveGridlayout(
    modifier: Modifier = Modifier,
    universalRadius: Dp,
    universalGap: Dp,
    labelToBeShown: Boolean = true,
    onClicking: () -> Unit,
    images: List<Uri> = emptyList(),
    backgroundColor: Color = MyCustomGray
) {
    Box(modifier = modifier
        .fillMaxWidth()

        .clickable {
            onClicking()
        }
        .background(backgroundColor)) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (labelToBeShown) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "5 GRID",
                        fontFamily = pressStartFont,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp,
                        color = MyCustomGray
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp)) {
                Column(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topStart = universalRadius))
                            .background(color = Color.White)
                    ) {
                        images.getOrNull(0)?.let { ApniImageChane(uri = it, modifier = Modifier.fillMaxSize()) }
                    }
                    Spacer(modifier = Modifier.height(universalGap))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(color = Color.White)
                    ) {
                        images.getOrNull(1)?.let { ApniImageChane(uri = it, modifier = Modifier.fillMaxSize()) }
                    }
                    Spacer(modifier = Modifier.height(universalGap))

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomStart = universalRadius))
                            .background(color = Color.White)
                    ) {
                        images.getOrNull(2)?.let { ApniImageChane(uri = it, modifier = Modifier.fillMaxSize()) }
                    }
                }
                Spacer(modifier = Modifier.width(universalGap))
                Column(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topEnd = universalRadius))
                            .background(color = Color.White)
                    ) {
                        images.getOrNull(3)?.let { ApniImageChane(uri = it, modifier = Modifier.fillMaxSize()) }
                    }
                    Spacer(modifier = Modifier.height(universalGap))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomEnd = universalRadius))
                            .background(color = Color.White)
                    ) {
                        images.getOrNull(4)?.let { ApniImageChane(uri = it, modifier = Modifier.fillMaxSize()) }
                    }
                }
            }
        }
    }
}