package com.refanzzzz.palmoildetection.ui.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refanzzzz.palmoildetection.data.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : ViewModel() {
    val isOnboardingCompleted = onboardingRepository.currentOnboardingStatus.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    fun setCompletedOnboarding() {
        viewModelScope.launch {
            onboardingRepository.saveOnboardingStatus(true)
        }
    }
}