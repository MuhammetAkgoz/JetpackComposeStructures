package com.example.domain.model

import com.example.core.error.Failure

data class ErrorModel (
    val message: String,
    val title: String,
    val errorCode: String,
    val errorType: String
)


fun Failure.asError(): ErrorModel {
    return when (this) {
        is Failure.NetworkFailure -> ErrorModel(
            title = "Bağlantı Hatası",
            message = "İnternet bağlantınızı kontrol ediniz. Lütfen tekrar deneyin.",
            errorCode = "NET-001",
            errorType = "NETWORK"
        )

        is Failure.ServerFailure -> ErrorModel(
            title = "Sunucu Hatası",
            message = message.ifBlank { "Sunucudan beklenmedik bir cevap alındı." },
            errorCode = code.toString(),
            errorType = "SERVER"
        )


        is Failure.UnknownFailure -> ErrorModel(
            title = "Hata",
            message = throwable.localizedMessage ?: "Beklenmedik bir hata oluştu.",
            errorCode = "UNK-999",
            errorType = "UNKNOWN"
        )

        is Failure.DefaultFailure -> ErrorModel(
            title = "Belirlenemeyen Hata",
            message = message,
            errorCode = "31313131",
            errorType = "UNSPECIFIED "
        )
    }
}