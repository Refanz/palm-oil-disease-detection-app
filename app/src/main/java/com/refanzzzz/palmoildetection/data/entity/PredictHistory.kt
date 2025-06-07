package com.refanzzzz.palmoildetection.data.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "histories")
data class PredictHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val predictDate: String,
    val explain: String,
    val imageUri: String
)

data class PredictHistoryItem(
    val explain: String,
    val imageUri: String,
    val predictDate: String
)