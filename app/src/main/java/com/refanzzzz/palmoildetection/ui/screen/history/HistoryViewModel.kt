package com.refanzzzz.palmoildetection.ui.screen.history

import androidx.lifecycle.ViewModel
import com.refanzzzz.palmoildetection.data.model.HistoryItem
import com.refanzzzz.palmoildetection.data.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
): ViewModel() {
    fun getHistories(): Flow<List<HistoryItem>> {
        return historyRepository.getDummyHistory()
    }
}