package com.example.presentation.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun DetailScreen(
    onBottomSheet: () -> Unit,
    onAlertDialog: () -> Unit,
    onDialog: () -> Unit,
    onNavigateProfile: () -> Unit,
    viewModel: DetailViewModel =  viewModel()) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
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