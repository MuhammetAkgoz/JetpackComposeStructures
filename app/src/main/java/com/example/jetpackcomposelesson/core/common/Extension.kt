package com.example.jetpackcomposelesson.core.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController


fun <T> NavController.popBackStackWithResult(key: String, result: T) {
    previousBackStackEntry?.savedStateHandle?.set(key, result)
    popBackStack()
}

@Composable
fun <T> NavBackStackEntry.observeResult(key: String): State<T?> {
    return savedStateHandle.getStateFlow<T?>(key, null).collectAsState()
}