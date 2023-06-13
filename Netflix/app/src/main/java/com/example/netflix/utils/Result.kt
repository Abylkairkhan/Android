package com.example.netflix.utils

sealed class Result<T> {
}

class PendingResult<T>: Result<T>()

class SuccessResult<T>(val data: T): Result<T>()

class ErrorResult<T>(val exception: Exception): Result<T>()

fun <T> Result<T>?.takeSuccess(): T? {
    return if (this is SuccessResult)
        this.data
    else
        null
}