package com.cherish.mynewtodoapp.data.remote

sealed class ResultManager<out T> {
    data class Failure(val error: Throwable? = null): ResultManager<Nothing>()
    data class Success<T>(val data : T): ResultManager<T>()
    data class Loading(val status: Boolean = true): ResultManager<Nothing>()
}