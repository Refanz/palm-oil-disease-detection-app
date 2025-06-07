package com.refanzzzz.palmoildetection.ui.screen.prediction

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.refanzzzz.palmoildetection.data.response.ResponseState
import com.refanzzzz.palmoildetection.ui.component.ScanLoading
import com.refanzzzz.palmoildetection.ui.component.history.DiseaseIcon
import com.refanzzzz.palmoildetection.util.AppUtil.getImageFile
import com.refanzzzz.palmoildetection.util.AppUtil.toFormattedFloat2Decimal

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PredictionScreen(
    navController: NavController,
    imageUri: Uri
) {
    val predictionViewModel = hiltViewModel<PredictionViewModel>()
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        predictionViewModel.predict(imageUri.getImageFile(context))
    }

    when (val result = predictionViewModel.predictResult.value) {
        is ResponseState.Loading -> ScanLoading()

        is ResponseState.Error -> {
            Toast.makeText(context, result.error, Toast.LENGTH_SHORT).show()
        }

        is ResponseState.Success -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState)
            ) {
                GlideImage(
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .size(200.dp),
                    model = imageUri,
                    contentDescription = "Processed Image",
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    result.data.predictions.map {
                        PredictionResultItem(it.prediction, it.confidence)
                    }

                    PredictionExplain()

                    PredictionAction(navController)
                }
            }
        }
    }
}

@Composable
fun PredictionExplain() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(PaddingValues(12.dp))
    ) {
        Text(
            text = "Explanation",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = """
Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. 

Duis autem vel eum iriure dol
Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis. 

At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.
                    """.trimIndent(),
        )
    }
}

@Composable
fun PredictionResultItem(
    label: String,
    confidence: Float
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(PaddingValues(12.dp))
    ) {
        DiseaseIcon(label)
        Column {
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "${confidence.toFormattedFloat2Decimal()}% confidence"
            )
        }
    }
}

@Composable
fun PredictionAction(
    navController: NavController
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            border = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary
            ),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
            contentPadding = PaddingValues(12.dp),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {}
        ) {
            Text(
                text = "Save to History",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            border = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary
            ),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
            contentPadding = PaddingValues(12.dp),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {}
        ) {
            Text(
                text = "Scan Again",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            contentPadding = PaddingValues(12.dp),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {}
        ) {
            Text(
                text = "Learn More",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewPredictionScreen() {
//    PredictionScreen()
    Text("aaa")
}