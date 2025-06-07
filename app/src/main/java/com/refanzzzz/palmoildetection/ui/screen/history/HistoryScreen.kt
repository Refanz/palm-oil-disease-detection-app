package com.refanzzzz.palmoildetection.ui.screen.history

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.refanzzzz.palmoildetection.data.entity.PredictHistoryWithDisease
import com.refanzzzz.palmoildetection.data.response.ResponseState
import com.refanzzzz.palmoildetection.navigation.Screen
import com.refanzzzz.palmoildetection.ui.component.ScanLoading
import com.refanzzzz.palmoildetection.ui.component.history.DiseaseIcon

@Composable
fun HistoryScreen(
    navController: NavController
) {

    val historyViewModel = hiltViewModel<HistoryViewModel>()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        historyViewModel.getPredictHistories()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "History",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )

        when (val histories = historyViewModel.predictHistories.value) {
            is ResponseState.Loading -> {
                ScanLoading()
            }

            is ResponseState.Error -> {
                Toast.makeText(context, histories.error, Toast.LENGTH_SHORT).show()
            }

            is ResponseState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(histories.data) { item ->
                        HistoryItem(item) {
                            navController.navigate(Screen.PredictionHistory.createRoute(it))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryItem(item: PredictHistoryWithDisease, onItemClick: (id: String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
            .clickable(true) {
                onItemClick(item.predictHistory.id.toString())
            }
    ) {
        DiseaseIcon(disease = item.predictDiseases.first().name)
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = item.predictDiseases.first().name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.predictHistory.predictDate,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHistoryScreen() {
    HistoryScreen(navController = rememberNavController())
}