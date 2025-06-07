package com.refanzzzz.palmoildetection.di

import android.content.Context
import androidx.room.Room
import com.refanzzzz.palmoildetection.data.dao.PredictHistoryDao
import com.refanzzzz.palmoildetection.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "palm_oil_database"
        ).build()
    }

    @Provides
    fun providePredictHistoryDao(appDatabase: AppDatabase): PredictHistoryDao {
        return appDatabase.predictHistoryDao()
    }
}