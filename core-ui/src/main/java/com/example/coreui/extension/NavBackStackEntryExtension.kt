package com.example.coreui.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavBackStackEntry

@Composable
fun <T> NavBackStackEntry.observeResult(key: String): State<T?> {
    return savedStateHandle.getStateFlow<T?>(key, null).collectAsState()
}