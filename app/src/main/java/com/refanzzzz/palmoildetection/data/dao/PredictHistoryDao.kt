package com.refanzzzz.palmoildetection.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.refanzzzz.palmoildetection.data.entity.PredictDisease
import com.refanzzzz.palmoildetection.data.entity.PredictDiseaseItem
import com.refanzzzz.palmoildetection.data.entity.PredictHistory
import com.refanzzzz.palmoildetection.data.entity.PredictHistoryItem
import com.refanzzzz.palmoildetection.data.entity.PredictHistoryWithDisease

@Dao
interface PredictHistoryDao {
    @Transaction
    @Query("SELECT * FROM histories")
    suspend fun getHistories(): List<PredictHistory>

    @Transaction
    @Query("SELECT * FROM histories ORDER BY id DESC")
    suspend fun getHistoriesWithDiseases(): List<PredictHistoryWithDisease>

    @Transaction
    @Query("SELECT * FROM histories WHERE id = :id")
    suspend fun getHistoryWithDiseaseById(id: Int): PredictHistoryWithDisease

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = PredictHistory::class)
    suspend fun insertHistory(predictHistory: PredictHistoryItem): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = PredictDisease::class)
    suspend fun insertDisease(predictDisease: PredictDiseaseItem)
}