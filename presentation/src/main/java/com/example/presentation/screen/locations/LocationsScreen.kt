package com.example.presentation.screen.locations

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coreui.builder.ScreenStateBuilder
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun LocationsScreen(viewModel: LocationsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .distinctUntilChanged()
            .filter { it.isNotEmpty() }
            .collectLatest { visibleItems ->
                val lastVisibleItem = visibleItems.last()
                val totalItems = listState.layoutInfo.totalItemsCount


                if (lastVisibleItem.index >= totalItems - 4 && !state.isLoadingMore) {
                    val page = totalItems / 20 + 1
                    viewModel.setEvent(LocationsEvent.LoadData(page))
                }
            }
    }

    LocationContent(
        state = state,
        listState = listState
    )
}

@Composable
fun LocationContent(
    state: LocationsState,
    listState: LazyListState,
) {

    ScreenStateBuilder(
        state = state
    ) {
        LazyColumn(state = listState) {
            items(state.locations, key = { it.name }) { location ->
                ListItem(
                    leadingContent = {
                        Text(location.id.toString())
                    },
                    headlineContent = {
                        Text(location.name)
                    },
                    supportingContent = {
                        Text(location.dimension ?: "")
                    }
                )
            }

            item {
                if (state.isLoadingMore) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        LinearProgressIndicator()
                    }
                }

            }
        }

    }
}