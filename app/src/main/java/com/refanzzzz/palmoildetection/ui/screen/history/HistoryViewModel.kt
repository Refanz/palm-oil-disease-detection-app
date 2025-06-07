package com.refanzzzz.palmoildetection.ui.screen.history

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refanzzzz.palmoildetection.data.entity.PredictHistoryWithDisease
import com.refanzzzz.palmoildetection.data.repository.HistoryRepository
import com.refanzzzz.palmoildetection.data.response.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
) : ViewModel() {

    private val _predictHistories: MutableState<ResponseState<List<PredictHistoryWithDisease>>> =
        mutableStateOf(ResponseState.Loading)
    val predictHistories: State<ResponseState<List<PredictHistoryWithDisease>>> = _predictHistories

    private val _history: MutableState<ResponseState<PredictHistoryWithDisease>> = mutableStateOf(
        ResponseState.Loading
    )
    val history: State<ResponseState<PredictHistoryWithDisease>> = _history

    fun getPredictHistories() {
        viewModelScope.launch {
            historyRepository.getHistories().onStart {
                _predictHistories.value = ResponseState.Loading
            }.catch {
                _predictHistories.value = ResponseState.Error(it.message.toString())
            }.collect {
                _predictHistories.value = it
            }
        }
    }

    fun getPredictHistoryById(id: Int) {
        viewModelScope.launch {
            historyRepository.getHistoryWithDiseaseById(id).onStart {
                _history.value = ResponseState.Loading
            }.catch {
                _history.value = ResponseState.Error(it.message ?: "")
            }.collect {
                _history.value = it
            }
        }
    }
}