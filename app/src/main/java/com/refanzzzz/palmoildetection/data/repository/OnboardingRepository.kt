package com.refanzzzz.palmoildetection.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.refanzzzz.palmoildetection.R
import com.refanzzzz.palmoildetection.data.model.OnboardingDataItem
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class OnboardingRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private val onboardingItems = listOf(
        OnboardingDataItem(
            title = "Camera Detection",
            desc = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam",
            imageResId = R.drawable.first_onboarding
        ),
        OnboardingDataItem(
            title = "Detection History",
            desc = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam",
            imageResId = R.drawable.second_onboardning
        ),
        OnboardingDataItem(
            title = "AI-Based Explanation",
            desc = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam",
            imageResId = R.drawable.thrid_onboarding
        )
    )

    val currentOnboardingStatus: Flow<Boolean> = dataStore.data.map { pref ->
        pref[onboardingKey] ?: false
    }

    suspend fun saveOnboardingStatus(onboardingCompleted: Boolean) {
        dataStore.edit { pref ->
            pref[onboardingKey] = onboardingCompleted
        }
    }


    fun getOnboardingItems() = flow {
        emit(onboardingItems)
    }

    companion object {
        private val onboardingKey = booleanPreferencesKey("onboarding_completed")
    }
}