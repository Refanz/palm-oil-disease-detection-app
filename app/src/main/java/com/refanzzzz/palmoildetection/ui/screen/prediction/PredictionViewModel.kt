package com.refanzzzz.palmoildetection.ui.screen.prediction

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refanzzzz.palmoildetection.data.repository.PredictRepository
import com.refanzzzz.palmoildetection.data.response.PredictResponse
import com.refanzzzz.palmoildetection.data.response.ResponseState
import com.refanzzzz.palmoildetection.service.classifier.ImageProcessor
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.File
import java.io.InputStream
import java.nio.ByteBuffer

@HiltViewModel
class PredictionViewModel @Inject constructor(
    private val predictRepository: PredictRepository
) : ViewModel() {

    private val _predictResult: MutableState<ResponseState<PredictResponse>> = mutableStateOf(
        ResponseState.Loading
    )
    val predictResult: State<ResponseState<PredictResponse>> = _predictResult

    fun predict(imageFile: File) {
        viewModelScope.launch(Dispatchers.IO) {
            predictRepository.predict(imageFile).onStart {
                _predictResult.value = ResponseState.Loading
            }.catch {
                _predictResult.value = ResponseState.Error(it.message ?: "")
            }.collect {
                _predictResult.value = it
            }
        }
    }

    companion object {
        private const val TAG = "PredictionViewModel"
    }
}