package com.anshul.collagemaker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
@Preview(showSystemUi = true)
fun SplashScreen(navController: NavHostController?=null) {
    LaunchedEffect(Unit) {
        delay(2500) // 2.5 seconds
        navController?.navigate("home")
    }
    Scaffold(containerColor = Color(0xFF2A2A2A),
        modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.padding(it)){
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(contentDescription = "", modifier = Modifier.size(70.dp).align(Alignment.CenterHorizontally), painter = painterResource(R.drawable.logo), alignment = Alignment.Center)
            }
        }
    }


}