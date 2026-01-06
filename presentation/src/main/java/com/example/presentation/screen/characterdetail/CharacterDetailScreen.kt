package com.example.presentation.screen.characterdetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coreui.component.builder.ScreenStateBuilder


@Composable
fun CharacterDetailScreen(viewModel: CharacterDetailViewModel = hiltViewModel()){
    val state by viewModel.state.collectAsStateWithLifecycle()

    CharacterDetailContent(state)
}

@Composable
private fun CharacterDetailContent(state: CharacterDetailState) {

    ScreenStateBuilder(state = state) {
        Text("deneme")
    }
}