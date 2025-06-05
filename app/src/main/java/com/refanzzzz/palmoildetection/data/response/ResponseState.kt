package com.refanzzzz.palmoildetection.data.response

sealed class ResponseState<out R> private constructor() {
    data class Success<out T>(val data: T) : ResponseState<T>()
    data class Error(val error: String) : ResponseState<Nothing>()
    object Loading : ResponseState<Nothing>()
}