package com.refanzzzz.palmoildetection.ui.screen.prediction

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refanzzzz.palmoildetection.data.model.PredictionResult
import com.refanzzzz.palmoildetection.service.classifier.ImageProcessor
import com.refanzzzz.palmoildetection.service.classifier.PalmOilDiseaseClassifier
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.InputStream
import java.nio.ByteBuffer

@HiltViewModel
class PredictionViewModel @Inject constructor(
    private val palmOilDiseaseClassifier: PalmOilDiseaseClassifier,
    private val imageProcessor: ImageProcessor,
) : ViewModel() {

    private val _predictionResult = MutableStateFlow<List<PredictionResult>?>(null)
    val predictionResult: StateFlow<List<PredictionResult>?> = _predictionResult

    fun convertByteBufferToBitmap(byteBuffer: ByteBuffer, width: Int, height: Int): Bitmap {
        return imageProcessor.convertByteBufferToBitmap(byteBuffer, width, height)
    }

    fun processingImage(inputStream: InputStream, rotationDegrees: Int): ByteBuffer {
        var originalBitmap: Bitmap? = null
        var decodedBitmap: ByteBuffer? = null

        try {
            originalBitmap = BitmapFactory.decodeStream(inputStream)

            if (originalBitmap != null) {
                val rotatedBitmap = imageProcessor.rotateBitmap(originalBitmap, rotationDegrees)
                val inputBuffer = imageProcessor.convertBitmapToByteBuffer(rotatedBitmap)

                decodedBitmap = inputBuffer
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
        } finally {
            inputStream.close()
            originalBitmap?.recycle()
        }

        return decodedBitmap!!
    }

    fun predictDisease(imgPrediction: ByteBuffer) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = palmOilDiseaseClassifier.classifyImage(imgPrediction)
            _predictionResult.value = result
        }
    }

    companion object {
        private const val TAG = "PredictionViewModel"
    }
}