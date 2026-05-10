package com.anshul.collagemaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.anshul.collagemaker.HomeScreen.EditScreen
import com.anshul.collagemaker.HomeScreen.HomeScreen
import com.anshul.collagemaker.HomeScreen.SavedProjects

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppNavigation()
        }
    }

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {

            composable("edit") {
                EditScreen(navController)
            }

            composable("splash") {
                SplashScreen(navController)
            }


            composable("compose") {
                FreeHandCompose()
            }

            composable("home") {
                HomeScreen(navController)
            }
            composable("saved") {
                SavedProjects()
            }
            composable(
                route = "select/{count}",
                arguments = listOf(
                    navArgument("count") { type = NavType.IntType }
                )
            ) { backStackEntry ->

                val count = backStackEntry.arguments?.getInt("count") ?: 0

                PhotosSelector(navController, count)

            }
        }
    }


}
