package com.refanzzzz.palmoildetection.service.classifier

import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.core.graphics.scale
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.inject.Inject
import androidx.core.graphics.createBitmap

class ImageProcessor @Inject constructor() {
    fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(4 * MODEL_INPUT_SIZE * MODEL_INPUT_SIZE * 3)
        byteBuffer.order(ByteOrder.nativeOrder())

        val resizedBitmap = bitmap.scale(MODEL_INPUT_SIZE, MODEL_INPUT_SIZE)

        val intValues = IntArray(MODEL_INPUT_SIZE * MODEL_INPUT_SIZE)
        resizedBitmap.getPixels(
            intValues,
            0,
            resizedBitmap.width,
            0,
            0,
            resizedBitmap.width,
            resizedBitmap.height
        )

        var pixel = 0
        for (i in 0 until MODEL_INPUT_SIZE) {
            for (j in 0 until MODEL_INPUT_SIZE) {
                val `val` = intValues[pixel++]
                byteBuffer.putFloat(((`val` shr 16 and 0xFF) / 255.0f))
                byteBuffer.putFloat(((`val` shr 8 and 0xFF) / 255.0f))
                byteBuffer.putFloat(((`val` and 0xFF) / 255.0f))
            }
        }

        return byteBuffer
    }

    fun rotateBitmap(bitmap: Bitmap, rotationDegrees: Int): Bitmap {
        if (rotationDegrees == 0) return bitmap

        val matrix = Matrix()
        matrix.postRotate(rotationDegrees.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    fun convertByteBufferToBitmap(byteBuffer: ByteBuffer, width: Int, height: Int): Bitmap {
        byteBuffer.rewind()

        val bitmap = createBitmap(width, height)
        val intValues = IntArray(width * height)

        var pixel = 0
        for (i in 0 until height) {
            for (j in 0 until width) {
                var r = (byteBuffer.float * 255).toInt().coerceIn(0, 255)
                var g = (byteBuffer.float * 255).toInt().coerceIn(0, 255)
                var b = (byteBuffer.float * 255).toInt().coerceIn(0, 255)
                intValues[pixel++] = (0xFF shl 24) or (r shl 16) or (g shl 8) or b
            }
        }

        bitmap.setPixels(intValues, 0, width, 0, 0, width, height)
        return bitmap

    }

    companion object {
        private const val MODEL_INPUT_SIZE = 224
    }
}