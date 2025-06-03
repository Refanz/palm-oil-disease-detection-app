package com.refanzzzz.palmoildetection.service.classifier

import com.refanzzzz.palmoildetection.data.model.PredictionResult
import com.refanzzzz.palmoildetection.ml.PalmOilModel
import com.refanzzzz.palmoildetection.util.LabelPrediction
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import javax.inject.Inject

class PalmOilDiseaseClassifier @Inject constructor(private val model: PalmOilModel?) {
    fun classifyImage(inputBuffer: ByteBuffer): PredictionResult {
        model?.let {
            val inputFeature =
                TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
            inputFeature.loadBuffer(inputBuffer)

            val outputs = it.process((inputFeature))
            val outputFeature = outputs.outputFeature0AsTensorBuffer

            val probabilities = outputFeature.floatArray

            val maxProbabilityIndex = probabilities.indices.maxByOrNull { probabilities[it] } ?: -1
            val predictedLabel = if (maxProbabilityIndex != -1) {
                LabelPrediction.getLabelByIndex(maxProbabilityIndex)
            } else {
                "Unknown Prediction"
            }

            val confidence = probabilities[maxProbabilityIndex] * 100
            return PredictionResult(predictedLabel, confidence)
        } ?: run {
            throw IllegalArgumentException("Model has not been initialized")
        }
    }

    fun close() {
        model!!.close()
    }
}