package com.refanzzzz.palmoildetection.ui.component.navbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.refanzzzz.palmoildetection.navigation.Screen

@Composable
fun NavBottomItem(modifier: Modifier = Modifier, screen: Screen, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .clickable {
                onClick()
            }
    ) {
        Icon(
            imageVector = screen.icon,
            contentDescription = screen.title,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Text(screen.title, color = MaterialTheme.colorScheme.primary)

    }
}

@Preview
@Composable
fun PreviewNavBottomItem() {
    NavBottomItem(screen = Screen.Home) {

    }
}