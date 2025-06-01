package com.refanzzzz.palmoildetection.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Main : Screen
}