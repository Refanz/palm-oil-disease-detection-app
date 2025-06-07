package com.refanzzzz.palmoildetection.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.refanzzzz.palmoildetection.data.dao.PredictHistoryDao
import com.refanzzzz.palmoildetection.data.entity.PredictDisease
import com.refanzzzz.palmoildetection.data.entity.PredictHistory

@Database(
    entities = [PredictHistory::class, PredictDisease::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun predictHistoryDao(): PredictHistoryDao
}