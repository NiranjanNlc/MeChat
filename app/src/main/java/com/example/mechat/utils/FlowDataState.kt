package com.example.mechat.utils

sealed class FlowDataState<out R> {
    data class Success<out T>(val data: T) : FlowDataState<T>()
    data class Error(val throwable: Throwable) : FlowDataState<Nothing>()
}