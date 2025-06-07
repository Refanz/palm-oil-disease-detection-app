package com.refanzzzz.palmoildetection.data.repository

import com.refanzzzz.palmoildetection.data.dao.PredictHistoryDao
import com.refanzzzz.palmoildetection.data.entity.PredictDiseaseItem
import com.refanzzzz.palmoildetection.data.entity.PredictHistoryItem
import com.refanzzzz.palmoildetection.data.entity.PredictHistoryWithDisease
import com.refanzzzz.palmoildetection.data.response.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HistoryRepository @Inject constructor(private val predictHistoryDao: PredictHistoryDao) {

    suspend fun saveHistoryWithDiseases(
        predictHistory: PredictHistoryItem,
        diseases: List<PredictDiseaseItem>
    ) {
        val historyId = predictHistoryDao.insertHistory(predictHistory)

        diseases.forEach { disease ->
            val disease = disease.copy(historyId = historyId)
            predictHistoryDao.insertDisease(disease)
        }
    }

    fun getHistories(): Flow<ResponseState<List<PredictHistoryWithDisease>>> = flow {
        emit(ResponseState.Loading)

        try {
            val histories = predictHistoryDao.getHistoriesWithDiseases()
            emit(ResponseState.Success(histories))
        } catch (e: Exception) {
            emit(ResponseState.Error(e.message ?: "Unknown error occurred"))
        }
    }
}