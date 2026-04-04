package com.example.presentation.screen.locations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.coreui.component.builder.ScreenStateBuilder
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun LocationsScreen(viewModel: LocationsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var currentIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(currentIndex, state.locations.size) {
        val totalItems = state.locations.size
        if (totalItems > 0 && currentIndex >= totalItems - 4 && !state.isLoadingMore) {
            val page = totalItems / 20 + 1
            viewModel.setEvent(LocationsEvent.LoadData(page))
        }
    }

    LocationContent(
        state = state,
        currentIndex = currentIndex,
        onPreviousClick = {
            if (currentIndex > 0) currentIndex--
        },
        onNextClick = {
            if (currentIndex < state.locations.size - 1) currentIndex++
        }
    )
}

@Composable
fun LocationContent(
    state: LocationsState,
    currentIndex: Int,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    ScreenStateBuilder(
        state = state
    ) {
        if (state.locations.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
            AnimatedContent(
                targetState = currentIndex,
                transitionSpec = {
                    fadeIn(tween(500)).togetherWith(fadeOut(tween(500)))
                },
                label = "location_icon_transition"
            ) { index ->
                val location = state.locations[index]
                // Main Image / Icon + Residents
                Box(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5F),
                    contentAlignment = Alignment.Center
                ) {

                    val residentImages = location.residentImages
                    val residentPositions = remember(location.id) {
                        val count = minOf(location.residentImages.size, 8)
                        if (count == 0) return@remember emptyList<Pair<Float, Float>>()

                        val rng = Random(location.id ?: 0)
                        val stepAngle = 360f / count
                        val startAngleOffset = rng.nextFloat() * 360f

                        List(count) { index ->
                            // Distribute into slices to avoid overlap, apply random wiggle room within the slice
                            val baseAngle =
                                startAngleOffset + (index * stepAngle) + (rng.nextFloat() * (stepAngle * 0.5f))
                            val baseRadius = 20f + rng.nextFloat() * 80f
                            Pair(baseAngle, baseRadius)
                        }
                    }

                    residentImages.take(8).forEachIndexed { index, url ->
                        val (baseAngle, baseRadius) = residentPositions[index]
                        val radBase = Math.toRadians(baseAngle.toDouble())

                        val offsetX = (cos(radBase) * baseRadius).dp
                        val offsetY = (sin(radBase) * baseRadius).dp

                        FloatingAvatar(
                            url = url,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(x = offsetX, y = offsetY),
                            seed = index
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

        // Navigation and Title
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onPreviousClick,
                enabled = currentIndex > 0,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Previous",
                    modifier = Modifier.size(36.dp)
                )
            }

            // Only animate the text here
            AnimatedContent(
                targetState = currentIndex,
                transitionSpec = {
                    if (targetState > initialState) {
                        (slideInHorizontally(tween(300)) { width -> width } + fadeIn(tween(300))).togetherWith(
                            slideOutHorizontally(tween(300)) { width -> -width } + fadeOut(
                                tween(300)
                            ))
                    } else {
                        (slideInHorizontally(tween(300)) { width -> -width } + fadeIn(tween(300))).togetherWith(
                            slideOutHorizontally(tween(300)) { width -> width } + fadeOut(
                                tween(300)
                            ))
                    }.using(SizeTransform(clip = false))
                },
                modifier = Modifier.weight(1f),
                label = "location_name_transition"
            ) { index ->
                val location = state.locations[index]
                Text(
                    text = location.name,
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            IconButton(
                onClick = onNextClick,
                enabled = currentIndex < state.locations.size - 1,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Next",
                    modifier = Modifier.size(36.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Card isolated below the arrows
        AnimatedContent(
            targetState = currentIndex,
            transitionSpec = {
                if (targetState > initialState) {
                    (slideInHorizontally(tween(300)) { width -> width } + fadeIn(tween(300))).togetherWith(
                        slideOutHorizontally(tween(300)) { width -> -width } + fadeOut(tween(300)))
                } else {
                    (slideInHorizontally(tween(300)) { width -> -width } + fadeIn(tween(300))).togetherWith(
                        slideOutHorizontally(tween(300)) { width -> width } + fadeOut(tween(300)))
                }.using(SizeTransform(clip = false))
            },
            modifier = Modifier.fillMaxWidth(),
            label = "location_card_transition"
        ) { index ->
            val location = state.locations[index]
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    LocationDetailRow("Type", location.type)
                    LocationDetailRow("Dimension", location.dimension)
                    LocationDetailRow("Residents", "${location.residents?.size ?: 0}")
                }
            }
            }
        }
        } else if (!state.isLoadingMore) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}


@Composable
fun LocationDetailRow(label: String, value: String?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = value.takeUnless { it.isNullOrBlank() } ?: "Unknown",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun FloatingAvatar(url: String, modifier: Modifier = Modifier, seed: Int) {
    val infiniteTransition = rememberInfiniteTransition(label = "floating")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000 + (seed * 800), easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "circular_motion"
    )

    val rotateRad = Math.toRadians(angle.toDouble())
    val wobbleRadius = 8f
    val motionX = (cos(rotateRad) * wobbleRadius).dp
    val motionY = (sin(rotateRad) * wobbleRadius).dp

    AsyncImage(
        model = url,
        contentDescription = "Resident",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .offset(x = motionX, y = motionY)
            .size(56.dp)
            .clip(CircleShape)
            .border(2.dp, MaterialTheme.colorScheme.primaryContainer, CircleShape)
    )
}