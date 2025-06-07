package com.refanzzzz.palmoildetection.navigation

import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String, val icon: ImageVector?, val title: String?) {
    object Loading : Screen("loading", null, "Loading")
    object Onboarding : Screen("onboarding", null, "Onboarding")
    object Home : Screen("home", Icons.Default.Home, "Home")
    object History : Screen("history", Icons.Default.History, "History")
    object Camera : Screen("camera", Icons.Default.Camera, "Camera")

    object PredictionHistory : Screen("predictionHistory/{historyId}", null, "History") {
        fun createRoute(historyId: String): String {
            return "predictionHistory/$historyId"
        }

        val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument("historyId") {
                    type = NavType.StringType
                    nullable = false
                })
    }

    object Prediction : Screen("prediction/{imageUri}", Icons.Default.ImageSearch, "Prediction") {
        fun createRoute(imageUri: Uri): String {
            val encodedUri = Uri.encode(imageUri.toString())
            return "prediction/$encodedUri"
        }

        val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument("imageUri") {
                    type = NavType.StringType
                    nullable = false
                })
    }

    object Preview : Screen("preview/{imageUri}", Icons.Default.ImageSearch, "Preview") {
        fun createRoute(imageUri: Uri): String {
            val encodedUri = Uri.encode(imageUri.toString())
            return "preview/$encodedUri"
        }

        val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument("imageUri") {
                    type = NavType.StringType
                    nullable = false
                })
    }

    companion object {
        fun getScreenTitleByRoute(route: String): String {
            val title = if (route.contains("home")) {
                "Home"
            } else if (route.contains("history")) {
                "History"
            } else if (route.contains("camera")) {
                "Scan"
            } else if (route.contains("prediction")) {
                "Prediction"
            } else if (route.contains("preview")) {
                "Preview"
            } else {
                ""
            }

            return title
        }
    }
}