package com.refanzzzz.palmoildetection.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.refanzzzz.palmoildetection.R
import com.refanzzzz.palmoildetection.navigation.Screen
import com.refanzzzz.palmoildetection.ui.component.CameraButton

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MainScreen(navController: NavController) {
    val mainViewModel = hiltViewModel<MainViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 32.sp,
                text = "Oil Palm\nDisease Detection".uppercase(),
                lineHeight = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp)
            ) {
                CameraButton {
                    navController.navigate("camera")
                }
                Text(
                    text = "Scan Now",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 28.sp
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = "Last Result",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Fungal Disease",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Monday, 20 Mei 2025",
                    fontSize = 16.sp
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp),
            ) {
                Text(
                    text = "Disease Detection Stats",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Healthy",
                            fontSize = 16.sp
                        )
                        Text(
                            text = "20",
                            fontSize = 16.sp
                        )
                    }
                    LinearProgressIndicator(
                        progress = { 0.2f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        trackColor = MaterialTheme.colorScheme.surface,
                    )
                }
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Infected",
                            fontSize = 16.sp
                        )
                        Text(
                            text = "20",
                            fontSize = 16.sp
                        )
                    }
                    LinearProgressIndicator(
                        progress = { 0.4f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = MaterialTheme.colorScheme.error,
                        trackColor = MaterialTheme.colorScheme.surface,
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    GlideImage(
                        model = R.drawable.tips,
                        contentDescription = "Tips",
                        modifier = Modifier
                            .size(48.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = "Quick Tips",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            fontSize = 16.sp,
                            text = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos"
                        )
                    }
                }
            }
        }

        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .fillMaxSize()

        ) {
            FloatingActionButton(
                shape = RoundedCornerShape(100.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    navController.navigate(Screen.Camera.route)
                },
                modifier = Modifier
                    .size(64.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Scan Floating Button"
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
//    MainScreen()
}