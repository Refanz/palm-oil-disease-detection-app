package com.refanzzzz.palmoildetection.ui.screen.preview

import android.net.Uri
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.refanzzzz.palmoildetection.data.response.ResponseState
import com.refanzzzz.palmoildetection.ui.component.ScanLoading
import com.refanzzzz.palmoildetection.util.AppUtil.getImageFile

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PreviewScreen(
    imageUri: Uri,
    navController: NavController
) {

    val previewViewModel = hiltViewModel<PreviewViewModel>()
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {

        when (val result = previewViewModel.predictResult.value) {
            is ResponseState.Loading -> {
                ScanLoading()
            }

            is ResponseState.Error -> {
                Toast.makeText(context, result.error, Toast.LENGTH_SHORT).show()
            }

            is ResponseState.Success -> {
                Toast.makeText(context, result.data.predictions.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {}
        }

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
                    previewViewModel.predict(imageUri.getImageFile()!!)
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