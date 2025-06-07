package com.refanzzzz.palmoildetection.ui.screen.prediction

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PredictionDetailScreen(
    imageUri: Uri
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPredictionDetailScreen() {
    PredictionDetailScreen(Uri.EMPTY)
}