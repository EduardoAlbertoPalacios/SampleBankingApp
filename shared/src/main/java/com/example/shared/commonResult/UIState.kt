package com.example.shared.commonResult

sealed class UIState<out T: Any> {
    object Loading : UIState<Nothing>()
    data class Success<out T: Any>(val items:  T) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
}