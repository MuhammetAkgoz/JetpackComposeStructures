package com.example.jetpackcomposelesson.core.common

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController


fun <T> NavController.popBackStackWithResult(key: String, result: T) {
    previousBackStackEntry?.savedStateHandle?.set(key, result)
    popBackStack()
}

fun <T> NavBackStackEntry.getResult(key: String): T? {
    val result = savedStateHandle.get<T>(key)
    if (result != null) {
        savedStateHandle.remove<String>(key)
    }
    return result
}