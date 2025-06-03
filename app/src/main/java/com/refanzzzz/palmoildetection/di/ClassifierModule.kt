package com.refanzzzz.palmoildetection.di

import android.content.Context
import com.refanzzzz.palmoildetection.ml.PalmOilModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClassifierModule {
    @Singleton
    @Provides
    fun providePalmOilModel(@ApplicationContext context: Context): PalmOilModel {
        return PalmOilModel.newInstance(context)
    }
}