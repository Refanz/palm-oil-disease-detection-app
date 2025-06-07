package com.refanzzzz.palmoildetection.navigation

sealed class ScreenState<out R> private constructor() {
    data class Show<out T>(val data: T) : ScreenState<T>()
    object Loading : ScreenState<Nothing>()
}