package com.refanzzzz.palmoildetection.service.classifier

import com.refanzzzz.palmoildetection.data.model.PredictionResult
import com.refanzzzz.palmoildetection.ml.PalmOilModel
import com.refanzzzz.palmoildetection.util.LabelPrediction
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import javax.inject.Inject

class PalmOilDiseaseClassifier @Inject constructor(private val model: PalmOilModel?) {
    fun classifyImage(inputBuffer: ByteBuffer): List<PredictionResult> {
        model?.let {
            val inputFeature =
                TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
            inputFeature.loadBuffer(inputBuffer)

            val outputs = it.process((inputFeature))
            val outputFeature = outputs.outputFeature0AsTensorBuffer
            val probabilities = outputFeature.floatArray

            val results = mutableListOf<PredictionResult>()

            probabilities.indices.map {
                results.add(
                    PredictionResult(
                        LabelPrediction.getLabelByIndex(it),
                        probabilities[it] * 100
                    )
                )
            }

            return results.sortedByDescending { it.confidence }
        } ?: run {
            throw IllegalArgumentException("Model has not been initialized")
        }
    }
}