package com.refanzzzz.palmoildetection.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.refanzzzz.palmoildetection.ui.screen.main.MainScreen

@Composable
fun NavigationScreen(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Main
    ) {
        composable<Screen.Main> {
            MainScreen()
        }
    }
}