package com.anshul.collagemaker.HomeScreen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.anshul.collagemaker.HomeScreen.Gridlayout.FiveGridlayout
import com.anshul.collagemaker.R
import com.anshul.collagemaker.ui.theme.MyCustomGray
import com.anshul.collagemaker.ui.theme.MyCustomWhite

val pressStartFont = FontFamily(
    Font(R.font.pressstart2p)
)

@Composable
@Preview(showSystemUi = true)

fun EditScreen() {


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
                        .padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
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
                FiveGridlayout(
                    modifier = Modifier.aspectRatio(1f),
                    universalGap = 8.dp,
                    universalRadius = 30.dp,
                    labelToBeShown = false
                )
            }
        }
    }


}

