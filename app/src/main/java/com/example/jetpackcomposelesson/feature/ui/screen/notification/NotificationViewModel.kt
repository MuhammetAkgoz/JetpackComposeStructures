package com.example.jetpackcomposelesson.feature.ui.screen.notification

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationViewModel : ViewModel() {
    private val _state = MutableStateFlow(NotificationState("notification"))
    val state = _state.asStateFlow()
}