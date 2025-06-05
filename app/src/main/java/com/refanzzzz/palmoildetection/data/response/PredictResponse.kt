package com.refanzzzz.palmoildetection.data.response

import com.google.gson.annotations.SerializedName

data class PredictResponse(
    @SerializedName("predict_results")
    val predictions: List<PredictItem>
)

data class PredictItem(
    @SerializedName("label")
    val prediction: String,
    @SerializedName("confidence")
    val confidence: Double
)


