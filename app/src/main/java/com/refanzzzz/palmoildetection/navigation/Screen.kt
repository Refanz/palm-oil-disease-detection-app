package com.refanzzzz.palmoildetection.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector, val title: String) {
    object Home: Screen("home", Icons.Default.Home, "Home")
    object History: Screen("history", Icons.Default.History, "History")
}