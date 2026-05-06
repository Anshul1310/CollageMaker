package com.anshul.collagemaker.HomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
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
import com.anshul.collagemaker.ui.theme.MyCustomWhite

@Composable
@Preview(showSystemUi = true)
fun SavedProjects(){
    Scaffold(
        containerColor = Color(0xFF2A2A2A),
    ) {
        Column(modifier = Modifier.padding(it)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .padding(start = 20.dp, top = 12.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Saved Projects",
                    fontFamily = pressStartFont,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = MyCustomWhite
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .padding(start = 20.dp, top = 12.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Tap on the projects to edit it.",
                    fontFamily = pressStartFont,

                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 12.sp,
                    color = MyCustomWhite
                )
            }

        }
    }
}