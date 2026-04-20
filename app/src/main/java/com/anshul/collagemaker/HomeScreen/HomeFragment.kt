package com.anshul.collagemaker.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anshul.collagemaker.HomeScreen.Gridlayout.FiveGridlayout
import com.anshul.collagemaker.HomeScreen.Gridlayout.FourGridLayout
import com.anshul.collagemaker.HomeScreen.Gridlayout.Threegridlayout
import com.anshul.collagemaker.HomeScreen.Gridlayout.TwogridLayout
import com.anshul.collagemaker.ui.theme.MyCustomGray
import com.anshul.collagemaker.ui.theme.MyCustomWhite


@Composable
@Preview(showSystemUi = true)
fun HomeFragment() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MyCustomGray)
        .padding(20.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(start = 20.dp, top = 13.dp, bottom = 13.dp),
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
                .padding(start = 20.dp, top = 12.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,

            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "CHOOSE LAYOUT",
                fontFamily = pressStartFont,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = MyCustomWhite
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            TwogridLayout(modifier = Modifier
                .weight(1f)
                .height(210.dp), 15.dp, 5.dp)
            Threegridlayout(modifier = Modifier
                .weight(1f)
                .height(210.dp), 15.dp, 5.dp)
        }
        Spacer(modifier = Modifier.height(15.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            FourGridLayout(modifier = Modifier
                .weight(1f)
                .height(210.dp), 15.dp, 5.dp)
            FiveGridlayout(modifier = Modifier
                .weight(1f)
                .height(210.dp), 15.dp, 5.dp)
        }
    }
}
