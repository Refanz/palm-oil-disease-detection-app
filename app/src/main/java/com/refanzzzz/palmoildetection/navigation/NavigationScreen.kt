package com.refanzzzz.palmoildetection.navigation

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.refanzzzz.palmoildetection.ui.component.ScanLoading
import com.refanzzzz.palmoildetection.ui.component.navbar.NavBottomContainer
import com.refanzzzz.palmoildetection.ui.component.navbar.NavBottomItem
import com.refanzzzz.palmoildetection.ui.screen.camera.CameraScreen
import com.refanzzzz.palmoildetection.ui.screen.history.HistoryScreen
import com.refanzzzz.palmoildetection.ui.screen.main.MainScreen
import com.refanzzzz.palmoildetection.ui.screen.onboarding.OnboardingScreen
import com.refanzzzz.palmoildetection.ui.screen.onboarding.OnboardingViewModel
import com.refanzzzz.palmoildetection.ui.screen.prediction.PredictionScreen
import com.refanzzzz.palmoildetection.ui.screen.preview.PreviewScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen() {

    val onboardingViewModel = hiltViewModel<OnboardingViewModel>()
    val onboardingCompleted by onboardingViewModel.onboardingCompleted.collectAsStateWithLifecycle()

    val bottomNavBarScreens = listOf<String>(
        Screen.Home.route, Screen.History.route
    )

    val fullScreens = listOf<String>(
        Screen.Loading.route, Screen.Onboarding.route
    )

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            if (!bottomNavBarScreens.contains(currentRoute) && !fullScreens.contains(currentRoute)) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = Screen.getScreenTitleByRoute(currentRoute ?: ""),
                            fontSize = 24.sp
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                        scrolledContainerColor = Color.Transparent
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = { navController.popBackStack() }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    scrollBehavior = scrollBehaviour
                )
            }
        },
        bottomBar = {
            if (bottomNavBarScreens.contains(currentRoute)) {
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
            startDestination = when (onboardingCompleted) {
                is ScreenState.Loading -> Screen.Loading.route
                is ScreenState.Show -> if ((onboardingCompleted as ScreenState.Show<Boolean>).data) {
                    Screen.Home.route
                } else {
                    Screen.Onboarding.route
                }
            },
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Loading.route) {
                ScanLoading()
            }
            composable(Screen.Onboarding.route) {
                OnboardingScreen()
            }
            composable(Screen.Home.route) {
                MainScreen(navController)
            }
            composable(Screen.History.route) {
                HistoryScreen()
            }
            composable(Screen.Camera.route) {
                CameraScreen(navController)
            }
            composable(
                route = Screen.Preview.route,
                arguments = Screen.Preview.arguments
            ) { navBackStackEntry ->
                val imageUriString = navBackStackEntry.arguments?.getString("imageUri")
                val imageUri = imageUriString?.let { Uri.decode(it) }?.toUri()

                PreviewScreen(
                    navController = navController,
                    imageUri = imageUri!!
                )
            }
            composable(
                route = Screen.Prediction.route,
                arguments = Screen.Prediction.arguments
            ) { navBackStackEntry ->
                val imageUriString = navBackStackEntry.arguments?.getString("imageUri")
                val imageUri = imageUriString?.let { Uri.decode(it) }?.toUri()

                PredictionScreen(
                    navController = navController,
                    imageUri = imageUri!!
                )
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