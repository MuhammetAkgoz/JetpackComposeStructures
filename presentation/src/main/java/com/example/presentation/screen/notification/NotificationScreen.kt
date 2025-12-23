package com.example.presentation.screen.notification

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coreui.builder.ScreenStateBuilder


@Composable
fun NotificationScreen(viewModel: NotificationViewModel = viewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScreenStateBuilder(state = state) {
        Text(
            text = "Notification Screen",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}