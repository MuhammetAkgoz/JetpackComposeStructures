package com.example.presentation.screen.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.coreui.builder.ScreenStateBuilder
import com.example.domain.model.CharacterModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onRegisterScrollToTop: (() -> Unit) -> Unit
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        onRegisterScrollToTop {
            scope.launch {
                lazyStaggeredGridState.animateScrollToItem(0)
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeEffect.ShowToast -> {
                    Toast.makeText(context, effect.url, Toast.LENGTH_SHORT).show()
                }

                is HomeEffect.ShowErrorDialog -> {
                    print(effect.errorModel.message)
                }
            }
        }
    }

    LaunchedEffect(lazyStaggeredGridState) {
        snapshotFlow { lazyStaggeredGridState.layoutInfo.visibleItemsInfo }
            .distinctUntilChanged()
            .filter { it.isNotEmpty() }
            .collectLatest { visibleItems ->
                val lastVisibleItem = visibleItems.last()
                val totalItems = lazyStaggeredGridState.layoutInfo.totalItemsCount


                if (lastVisibleItem.index >= totalItems - 2 && !state.isLoadingMore) {
                    val page = totalItems / 20 + 1
                    viewModel.setEvent(HomeEvent.LoadData(page))
                }
            }
    }

    HomeContent(
        state = state,
        lazyGridState = lazyStaggeredGridState,
        onEvent = viewModel::setEvent
    )
}

@Composable
fun HomeContent(
    state: HomeState,
    lazyGridState: LazyStaggeredGridState,
    onEvent: (HomeEvent) -> Unit
) {

    ScreenStateBuilder(
        state = state
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalItemSpacing = 16.dp,
            state = lazyGridState,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(state.characters.size, key = { it }) { index ->
                val character = state.characters[index]


                val randomHeight = remember(character.id) {
                    Random(character.id).nextInt(150, 300).dp
                }


                CharacterItem(
                    character = character,
                    randomHeight = randomHeight
                )
            }

            if (state.isLoadingMore) {
                item(span = StaggeredGridItemSpan.FullLine) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

        }
    }
}

@Composable
fun CharacterItem(
    character: CharacterModel,
    randomHeight: Dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(randomHeight)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.5f),
                            Color.Black.copy(alpha = 0.9f)
                        )
                    )
                )
        )

        Text(
            text = character.name,
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        )
    }
}