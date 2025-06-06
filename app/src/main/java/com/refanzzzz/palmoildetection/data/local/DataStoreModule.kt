package com.refanzzzz.palmoildetection.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private const val ONBOARDING_PREFERENCES_NAME = "onboarding_preferences"

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = ONBOARDING_PREFERENCES_NAME
    )

    @Singleton
    @Provides
    fun provideOnboardingPreferences(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}