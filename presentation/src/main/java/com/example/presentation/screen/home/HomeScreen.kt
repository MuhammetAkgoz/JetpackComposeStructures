package com.example.presentation.screen.home

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coreui.builder.ScreenStateBuilder
import com.example.presentation.theme.LocalThemeManager

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeEffect.ShowToast -> {
                    Toast.makeText(context, effect.url, Toast.LENGTH_SHORT).show()
                }

                is HomeEffect.ShowError -> {
                    // İstersen burada Snackbar gösterebilirsin
                    // snackbarHostState.showSnackbar(effect.message)
                }

                is HomeEffect.ShowErrorDialog -> {
                    print(effect.errorModel.message)
                }
            }
        }
    }

    HomeContent(
        state = state,
        onEvent = viewModel::setEvent
    )
}

@Composable
fun HomeContent(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    val manager = LocalThemeManager.current

    ScreenStateBuilder(
        state = state
    ) {
        LazyColumn {
            items(state.characters) { character ->
                ListItem(
                    headlineContent = {
                        Text(character.name)
                    },
                    leadingContent = {
                        Text(character.id.toString())
                    },
                    trailingContent = {
                        IconButton(
                            onClick = { onEvent(HomeEvent.OnCharacterClick(character.origin.url)) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "default",
                            )
                        }
                    }

                )
            }
        }
    }
}