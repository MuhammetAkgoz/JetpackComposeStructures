package com.example.core.base

import com.example.core.error.Failure

enum class ViewStatus {
    INITIAL, // Sayfa ilk açıldığında (Henüz işlem başlamadı)
    LOADING, // Yükleniyor
    SUCCESS, // Veri geldi
    EMPTY,   // Veri geldi ama liste boş
    ERROR    // Hata oluştu
}

interface BaseState {
    val viewStatus: ViewStatus
    val error: Failure
        get() = Failure.unspecified()
}
