package com.refanzzzz.palmoildetection.ui.screen.preview

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refanzzzz.palmoildetection.data.repository.PredictRepository
import com.refanzzzz.palmoildetection.data.response.PredictResponse
import com.refanzzzz.palmoildetection.data.response.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.File

@HiltViewModel
class PreviewViewModel @Inject constructor(
    private val predictRepository: PredictRepository
) : ViewModel() {

    private val _predictResult: MutableState<ResponseState<PredictResponse>?> = mutableStateOf(
        null
    )
    val predictResult: State<ResponseState<PredictResponse>?> = _predictResult

    fun predict(imageFile: File) {
        viewModelScope.launch(Dispatchers.IO) {
            predictRepository.predict(imageFile).onStart {
                _predictResult.value = ResponseState.Loading
                delay(5000)
            }.catch {
                _predictResult.value = ResponseState.Error(it.message ?: "")
            }.collect {
                _predictResult.value = it
            }
        }
    }
}