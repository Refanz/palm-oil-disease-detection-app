package com.refanzzzz.palmoildetection.ui.screen.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refanzzzz.palmoildetection.data.remote.Test
import com.refanzzzz.palmoildetection.data.repository.PredictRepository
import com.refanzzzz.palmoildetection.data.response.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val predictRepository: PredictRepository
) : ViewModel() {

    private val _testResult: MutableState<ResponseState<Test>> = mutableStateOf(ResponseState.Loading)
    val testResult: State<ResponseState<Test>> = _testResult

    fun test() {
        viewModelScope.launch(Dispatchers.IO) {
            predictRepository.test().onStart {
                _testResult.value = ResponseState.Loading
            }.catch {
                _testResult.value = ResponseState.Error(it.message ?: "")
            }.collect {
                _testResult.value = it
            }
        }
    }
}