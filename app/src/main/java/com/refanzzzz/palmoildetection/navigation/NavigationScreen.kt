package com.refanzzzz.palmoildetection.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.refanzzzz.palmoildetection.ui.component.navbar.NavBottomContainer
import com.refanzzzz.palmoildetection.ui.component.navbar.NavBottomItem
import com.refanzzzz.palmoildetection.ui.screen.history.HistoryScreen
import com.refanzzzz.palmoildetection.ui.screen.main.MainScreen

@Composable
fun NavigationScreen() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val items = listOf<Screen>(Screen.Home, Screen.History)

            NavBottomContainer {
                items.forEach { screen ->
                    NavBottomItem(
                        screen = screen,
                        modifier = Modifier.weight(1f)
                    ) {
                        onBottomNavClick(navController, screen)
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                MainScreen()
            }
            composable(Screen.History.route) {
                HistoryScreen()
            }
        }
    }
}

fun onBottomNavClick(navController: NavHostController, screen: Screen) {
    navController.navigate(screen.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}