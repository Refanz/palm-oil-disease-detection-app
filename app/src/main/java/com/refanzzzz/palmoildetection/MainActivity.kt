package com.refanzzzz.palmoildetection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.refanzzzz.palmoildetection.navigation.NavigationScreen
import com.refanzzzz.palmoildetection.ui.theme.PalmoildetectionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PalmoildetectionTheme {
                NavigationScreen()
            }
        }
    }
}