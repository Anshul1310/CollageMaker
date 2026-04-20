package com.anshul.collagemaker.HomeScreen.Gridlayout

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anshul.collagemaker.HomeScreen.pressStartFont
import com.anshul.collagemaker.ui.theme.MyCustomGray

@Composable
@Preview(showSystemUi = true)
fun GridLayout() {
    val universalRadius = 30.dp
    val universalGap = 5.dp

    Threegridlayout(modifier = Modifier, universalRadius, universalGap)
}

@Composable
fun Threegridlayout(
    modifier: Modifier = Modifier,
    universalRadius: Dp,
    universalGap: Dp,
    labelToBeShown: Boolean = true
) {

    Box(modifier = modifier
        .fillMaxWidth()
        .height(300.dp)
        .background(MyCustomGray)) {
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
                        fontSize = 15.sp,
                        color = MyCustomGray
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
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
                ) { }
                Spacer(modifier = Modifier.width(universalGap))
                Column(modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topEnd = universalRadius))
                            .background(color = Color.White)
                    ) { }
                    Spacer(modifier = Modifier.height(universalGap))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomEnd = universalRadius))
                            .background(color = Color.White)
                    ) { }
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
    labelToBeShown: Boolean = true
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(300.dp)
        .background(MyCustomGray)) {
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
                        fontSize = 15.sp,
                        color = MyCustomGray
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
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
                ) { }
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
                ) { }


            }
        }


    }
}

@Composable
fun FourGridLayout(
    modifier: Modifier = Modifier,
    universalRadius: Dp,
    universalGap: Dp,
    labelToBeShown: Boolean = true
) {


    Box(modifier = modifier
        .fillMaxWidth()
        .height(300.dp)
        .background(MyCustomGray)) {
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
                        fontSize = 15.sp,
                        color = MyCustomGray
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topStart = 10.dp))
                            .background(color = Color.White)
                    ) { }
                    Spacer(modifier = Modifier.height(universalGap))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomStart = 10.dp))
                            .background(color = Color.White)
                    ) { }
                }
                Spacer(modifier = Modifier.width(universalGap))
                Column(modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topEnd = 10.dp))
                            .background(color = Color.White)
                    ) { }
                    Spacer(modifier = Modifier.height(universalGap))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomEnd = 10.dp))
                            .background(color = Color.White)
                    ) { }
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
    labelToBeShown: Boolean = true
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(300.dp)
        .background(MyCustomGray)) {
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
                        fontSize = 15.sp,
                        color = MyCustomGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))


            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topStart = 10.dp))
                            .background(color = Color.White)
                    ) { }
                    Spacer(modifier = Modifier.height(universalGap))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(color = Color.White)
                    ) { }
                    Spacer(modifier = Modifier.height(universalGap))

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomStart = 10.dp))
                            .background(color = Color.White)
                    ) { }
                }
                Spacer(modifier = Modifier.width(universalGap))
                Column(modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topEnd = 10.dp))
                            .background(color = Color.White)
                    ) { }
                    Spacer(modifier = Modifier.height(universalGap))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomEnd = 10.dp))
                            .background(color = Color.White)
                    ) { }
                }


            }
        }


    }
}
