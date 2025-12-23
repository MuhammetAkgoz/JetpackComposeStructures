package com.example.presentation.screen.search

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coreui.builder.ScreenStateBuilder

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScreenStateBuilder(state = state)  {
        Text(
            text = "Search Screen",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}