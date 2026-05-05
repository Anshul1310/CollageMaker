package com.anshul.collagemaker.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
@Preview(showSystemUi = true)
fun HomeScreen(navController: NavHostController) {
    var selectedBottomtab by remember { mutableStateOf("Home") }
    val navItems = listOf<NavItemModel>(
        NavItemModel("Home", Icons.Rounded.Home),
        NavItemModel("Create", Icons.Rounded.Create),
        NavItemModel("Settings", Icons.Rounded.Settings),
        NavItemModel("History", Icons.Rounded.FavoriteBorder)

    )
    Scaffold(bottomBar = {
        NavigationBar() {
            navItems.forEach { it ->

                NavigationBarItem(
                    onClick = { selectedBottomtab = it.name },
                    selected = selectedBottomtab == it.name,
                    icon = { Image(imageVector = it.icon, "") },
                    label = {
                        Text(text = it.name)
                    }
                )
            }
        }
    }) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            ContentScreen(selectedBottomtab,navController)
        }
    }
}

@Composable
fun ContentScreen(screenName: String, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        when (screenName) {
            "Home" -> HomeFragment(navController)
            "Settings" -> SettingsFragment()
            "Create" -> CreateFragment()
            "History" -> HistoryFragment()
        }

    }
}
