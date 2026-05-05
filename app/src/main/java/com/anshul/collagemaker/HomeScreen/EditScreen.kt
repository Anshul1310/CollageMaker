package com.anshul.collagemaker.HomeScreen

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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

@Composable
@Preview(showSystemUi = true)

fun EditScreen(navController: NavHostController?=null) {

    var radiusSeekbar by remember { mutableStateOf(23f) }
    val images = navController
        ?.previousBackStackEntry
        ?.savedStateHandle
        ?.get<List<Uri>>("selectedImages") ?: emptyList()
    Log.d("anshul",images.size.toString())

    Scaffold() {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .background(MyCustomGray)) {
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

                if(images.size ==5){
                    FiveGridlayout(
                        modifier = Modifier.aspectRatio(1f).padding(10.dp),
                        universalRadius = 30.dp,
                        universalGap = 8.dp,
                        images=images,
                        labelToBeShown = false,
                        onClicking = {}
                    )
                }else if(images.size==4){
                    FourGridLayout(
                        modifier = Modifier.aspectRatio(1f).padding(10.dp),
                        universalRadius = 30.dp,
                        universalGap = 8.dp,
                        labelToBeShown = false,
                        onClicking = {},
                        images=images
                    )
                }else if(images.size==3){
                    Threegridlayout(
                        modifier = Modifier.aspectRatio(1f).padding(10.dp),
                        universalRadius = 30.dp,
                        universalGap = 8.dp,
                        labelToBeShown = false,
                        onClicking = {},
                        images=images
                    )
                }else if(images.size==2){
                    TwogridLayout(
                        modifier = Modifier.aspectRatio(1f).padding(10.dp),
                        universalRadius = 30.dp,
                        universalGap = 8.dp,
                        labelToBeShown = false,
                        onClicking = {},
                        images=images
                    )
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

                Spacer(modifier = Modifier.height(20.dp))

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

                Row(modifier = Modifier.fillMaxWidth().background(color = MyCustomWhite,
                    shape = RoundedCornerShape(16.dp))) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(modifier = Modifier.weight(1f).padding(6.dp)){
                        Button(onClick = {}, modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MyCustomGray
                            ),) {
                            Text(text = "Save", fontSize = 15.sp, fontFamily = pressStartFont,
                                fontWeight = FontWeight.Normal,)
                        }
                    }
                    Box(modifier = Modifier.weight(1f).padding(6.dp), ){
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

