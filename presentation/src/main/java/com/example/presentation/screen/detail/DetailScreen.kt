package com.example.presentation.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coreui.component.builder.ScreenStateBuilder


@Composable
fun DetailScreen(
    onBottomSheet: () -> Unit,
    onAlertDialog: () -> Unit,
    onDialog: () -> Unit,
    onNavigateProfile: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScreenStateBuilder(state = state) {
        Column {
            Text(
                text = "Detail Screen",
                style = MaterialTheme.typography.headlineLarge
            )

            Button(onBottomSheet) { Text("Open Bottom Sheet") }
            Button(onAlertDialog) { Text("Open Alert Dialog") }
            Button(onDialog) { Text("Open Dialog") }
            Button(onNavigateProfile) { Text("Open Profile") }
        }
    }
}