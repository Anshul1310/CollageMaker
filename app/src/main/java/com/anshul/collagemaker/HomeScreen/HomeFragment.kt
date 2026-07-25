package com.anshul.collagemaker.HomeScreen

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anshul.collagemaker.HomeScreen.Gridlayout.FiveGridlayout
import com.anshul.collagemaker.HomeScreen.Gridlayout.FourGridLayout
import com.anshul.collagemaker.HomeScreen.Gridlayout.Threegridlayout
import com.anshul.collagemaker.HomeScreen.Gridlayout.TwogridLayout
import com.anshul.collagemaker.ui.theme.MyCustomGray
import com.anshul.collagemaker.ui.theme.MyCustomWhite
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
@Preview(showSystemUi = true)
fun HomeFragment(navController: NavController?=null) {
    var input by remember { mutableStateOf("") }
    var imageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }

    var temp = input.toIntOrNull() ?: 0
    var maxImages by remember { mutableStateOf(temp) }


    val permission = if (
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    ) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val permissionState = rememberPermissionState(
        permission = permission,
        onPermissionResult = { granted ->

            if (granted) {
                navController?.navigate("select/${maxImages}")
            }
        }
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MyCustomGray)
        .padding(12.dp)) {
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
                fontSize = 15.sp,
                color = MyCustomGray
            )
        }
        Spacer(modifier = Modifier.height(2.dp))

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
                fontSize = 12.sp,
                color = MyCustomWhite
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            TwogridLayout(
                modifier = Modifier
                    .weight(1f)
                    .height(210.dp),
                15.dp, 5.dp,
                onClicking = {
                    maxImages=2
                    if (permissionState.status.isGranted) {

                        navController?.navigate("select/2")
                    } else {
                        permissionState.launchPermissionRequest()
                    }
                },
            )
            Threegridlayout(
                modifier = Modifier
                    .weight(1f)
                    .height(210.dp),
                15.dp, 5.dp,
                onClicking = {
                maxImages=3
                    if (permissionState.status.isGranted) {

                        navController?.navigate("select/3")
                    } else {
                        permissionState.launchPermissionRequest()
                    }

                },
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            FourGridLayout(
                modifier = Modifier
                    .weight(1f)
                    .height(210.dp),
                15.dp, 5.dp,
                onClicking={
                    maxImages=4
                    if (permissionState.status.isGranted) {

                        navController?.navigate("select/4")
                    } else {
                        permissionState.launchPermissionRequest()
                    }
                },
            )
            FiveGridlayout(
                modifier = Modifier
                    .weight(1f)
                    .height(210.dp),
                15.dp, 5.dp,
                onClicking={
                maxImages=5
                    if (permissionState.status.isGranted) {

                        navController?.navigate("select/5")
                    } else {
                        permissionState.launchPermissionRequest()
                    }


                }
            )
        }

        Button(onClick = { navController?.navigate("compose") }) {
            Text(text = "Free Drawing Compose")
        }

    }
}

