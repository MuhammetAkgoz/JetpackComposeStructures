package com.example.core.error

import java.io.IOException

sealed class Failure {

    companion object {
        fun unspecified(): Failure {
            return DefaultFailure("Beklenmedik bir hata oluştu.")
        }
    }

    data class NetworkFailure(val exception: IOException) : Failure()
    data class ServerFailure(val code: Int, val message: String) : Failure()
    data class UnknownFailure(val throwable: Throwable) : Failure()
    data class DefaultFailure(val message: String) : Failure()
}