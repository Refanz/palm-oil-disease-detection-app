package com.refanzzzz.palmoildetection.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "diseases",
    foreignKeys = [
        ForeignKey(
            entity = PredictHistory::class,
            parentColumns = ["id"],
            childColumns = ["historyId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["historyId"])]
)
data class PredictDisease(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val confidence: Float,
    var historyId: Long
)

data class PredictDiseaseItem(
    val name: String,
    val confidence: Float,
    var historyId: Long?
)
