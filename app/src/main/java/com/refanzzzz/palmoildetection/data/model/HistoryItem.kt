package com.refanzzzz.palmoildetection.data.model

import androidx.compose.runtime.Immutable
import java.time.LocalDateTime

@Immutable
data class HistoryItem(
    val name: String,
    val timestamp: LocalDateTime,
    val description: String,
    val disease: String
)
