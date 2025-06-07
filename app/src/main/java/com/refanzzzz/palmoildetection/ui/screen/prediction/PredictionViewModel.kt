package com.refanzzzz.palmoildetection.ui.screen.prediction

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refanzzzz.palmoildetection.data.entity.PredictDiseaseItem
import com.refanzzzz.palmoildetection.data.entity.PredictHistoryItem
import com.refanzzzz.palmoildetection.data.repository.HistoryRepository
import com.refanzzzz.palmoildetection.data.repository.PredictRepository
import com.refanzzzz.palmoildetection.data.response.PredictItem
import com.refanzzzz.palmoildetection.data.response.PredictResponse
import com.refanzzzz.palmoildetection.data.response.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.File

@HiltViewModel
class PredictionViewModel @Inject constructor(
    private val predictRepository: PredictRepository,
    private val historyRepository: HistoryRepository
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


    fun savePredictHistory(predictHistory: PredictHistoryItem, diseases: List<PredictItem>, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            val diseaseList: List<PredictDiseaseItem> = diseases.map {
                PredictDiseaseItem(
                    name = it.prediction,
                    confidence = it.confidence,
                    historyId = null
                )
            }
            historyRepository.saveHistoryWithDiseases(predictHistory, diseaseList)
            onSuccess()
        }
    }

    companion object {
        private const val TAG = "PredictionViewModel"
    }
}