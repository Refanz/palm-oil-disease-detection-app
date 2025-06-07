package com.refanzzzz.palmoildetection.ui.screen.history

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.refanzzzz.palmoildetection.data.response.ResponseState
import com.refanzzzz.palmoildetection.ui.component.ScanLoading
import com.refanzzzz.palmoildetection.ui.component.history.DiseaseIcon
import com.refanzzzz.palmoildetection.util.AppUtil.toFormattedFloat2Decimal

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PredictionHistoryScreen(historyId: String) {

    val historyViewModel = hiltViewModel<HistoryViewModel>()
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        historyViewModel.getPredictHistoryById(historyId.toInt())
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .verticalScroll(scrollState)
    ) {
        when (val history = historyViewModel.history.value) {
            is ResponseState.Loading -> {
                ScanLoading()
            }

            is ResponseState.Error -> {
                Toast.makeText(context, history.error, Toast.LENGTH_SHORT).show()
            }

            is ResponseState.Success -> {
                val item = history.data

                GlideImage(
                    model = history.data.predictHistory.imageUri,
                    contentDescription = "history",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 12.dp,
                        alignment = Alignment.Start
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DiseaseIcon(
                        disease = item.predictDiseases.first().name,
                        modifier = Modifier
                            .size(75.dp)
                    )
                    Text(
                        text = item.predictDiseases.first().name,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    LinearProgressIndicator(
                        progress = { 0.76f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(25.dp),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.onPrimary
                    )

                    Text(
                        text = "Accuracy: ${item.predictDiseases.first().confidence.toFormattedFloat2Decimal()}",
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
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
                        .padding(PaddingValues(12.dp)),

                    ) {
                    Text(
                        text = "Explanation",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = item.predictHistory.explain
                    )
                }

                Button(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {}
                ) {
                    Text(
                        text = "Prediction Details",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}