package com.refanzzzz.palmoildetection.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val ONBOARDING_PREFERENCES_NAME = "onboarding_preferences"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = ONBOARDING_PREFERENCES_NAME
)