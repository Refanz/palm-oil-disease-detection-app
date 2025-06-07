package com.refanzzzz.palmoildetection.data.repository

import android.util.Log
import com.refanzzzz.palmoildetection.data.remote.ApiService
import com.refanzzzz.palmoildetection.data.response.PredictItem
import com.refanzzzz.palmoildetection.data.response.PredictResponse
import com.refanzzzz.palmoildetection.data.response.ResponseState
import jakarta.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File

class PredictRepository @Inject constructor(private val apiService: ApiService) {

    fun test() = flow {
        emit(ResponseState.Loading)

        try {
            val response = apiService.test()
            emit(ResponseState.Success(response))
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            emit(ResponseState.Error(e.message.toString()))
        }
    }.catch { except ->
        emit(ResponseState.Error("Unhandled flow exception: ${except.message}"))
    }

    fun predict(imageFile: File) = flow {
        emit(ResponseState.Loading)

        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "input_data",
            imageFile.name,
            requestImageFile
        )

        try {
            val successResponse = apiService.predict(multipartBody)

            val precessedResponse = successResponse.predictions.map {
                PredictItem(
                    prediction = it.prediction,
                    confidence = it.confidence * 100
                )
            }

            emit(
                ResponseState.Success(
                    PredictResponse(
                    precessedResponse.sortedByDescending { it.confidence }
                )
            ))
        } catch (e: HttpException) {
            Log.e(TAG, e.message, e)
            emit(ResponseState.Error(e.message.toString()))
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            emit(ResponseState.Error(e.message.toString()))
        }
    }.catch { except ->
        emit(ResponseState.Error("Unhandled flow exception: ${except.message}"))
    }

    companion object {
        private const val TAG = "PredictionRepository"
    }
}