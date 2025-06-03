package com.refanzzzz.palmoildetection.ui.screen.preview

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.refanzzzz.palmoildetection.navigation.Screen

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PreviewScreen(
    imageUri: Uri,
    navController: NavController
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {
        GlideImage(
            model = imageUri,
            contentDescription = "Image",
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Make sure the leaf is clearly visible.",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f),
                contentPadding = PaddingValues(16.dp),
                onClick = {
                    navController.navigate(Screen.Prediction.createRoute(imageUri))
                }
            ) {
                Text(
                    text = "Use Photo",
                    fontSize = 20.sp
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier
                    .weight(1f),
                onClick = {}
            ) {
                Text(
                    text = "Retake",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPredictionScreen() {
//    PredictionScreen()
}