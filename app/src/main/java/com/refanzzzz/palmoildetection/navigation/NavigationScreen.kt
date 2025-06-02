package com.refanzzzz.palmoildetection.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.refanzzzz.palmoildetection.ui.component.navbar.NavBottomContainer
import com.refanzzzz.palmoildetection.ui.component.navbar.NavBottomItem
import com.refanzzzz.palmoildetection.ui.screen.camera.CameraScreen
import com.refanzzzz.palmoildetection.ui.screen.history.HistoryScreen
import com.refanzzzz.palmoildetection.ui.screen.main.MainScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen() {

    val bottomNavBarScreens = listOf<String>(
        Screen.Home.route, Screen.History.route
    )

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (!bottomNavBarScreens.contains(currentRoute)) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = Screen.getScreenTitleByRoute(currentRoute ?: ""),
                            fontSize = 32.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { navController.popBackStack() }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (currentRoute != Screen.Camera.route) {
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
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                MainScreen(navController)
            }
            composable(Screen.History.route) {
                HistoryScreen()
            }
            composable(Screen.Camera.route) {
                CameraScreen()
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