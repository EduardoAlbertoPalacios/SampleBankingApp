package com.example.shared

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.onError(): Flow<T> = flow {
    try {
        collect {
            emit(it)
        }
    } catch (error: Exception) {
        throw Exception(error.message)
    }
}