package com.example.presentation.screen.profile

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coreui.builder.ScreenStateBuilder


@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onBack: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScreenStateBuilder(state = state) {
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { onBack("Say Hello") }) {
            Text(text = "Back")
        }
    }
}
