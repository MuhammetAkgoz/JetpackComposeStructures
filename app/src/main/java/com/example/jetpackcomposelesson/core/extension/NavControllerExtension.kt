package com.example.jetpackcomposelesson.core.extension

import androidx.navigation.NavController


fun <T> NavController.popBackStackWithResult(key: String, result: T) {
    previousBackStackEntry?.savedStateHandle?.set(key, result)
    popBackStack()
}