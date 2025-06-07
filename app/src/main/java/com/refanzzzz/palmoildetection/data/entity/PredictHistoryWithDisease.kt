package com.refanzzzz.palmoildetection.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PredictHistoryWithDisease(
    @Embedded
    val predictHistory: PredictHistory,
    @Relation(
        parentColumn = "id",
        entityColumn = "historyId"
    )
    val predictDiseases: List<PredictDisease>
)
