package com.example.presentation.navigation.transitions

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavBackStackEntry

object NetflixTransitions {

    private const val ENTER_DURATION = 400
    private const val EXIT_DURATION = 400

    val enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
        fadeIn(
            animationSpec = tween(ENTER_DURATION, easing = FastOutSlowInEasing)
        ) + scaleIn(
            initialScale = 0.95f,
            animationSpec = tween(ENTER_DURATION, easing = FastOutSlowInEasing)
        )
    }

    val exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
        fadeOut(
            animationSpec = tween(EXIT_DURATION, easing = FastOutSlowInEasing)
        ) + scaleOut(
            targetScale = 1.05f,
            animationSpec = tween(EXIT_DURATION, easing = FastOutSlowInEasing)
        )
    }

    val popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
        fadeIn(
            animationSpec = tween(ENTER_DURATION, easing = FastOutSlowInEasing)
        ) + scaleIn(
            initialScale = 1.05f,
            animationSpec = tween(ENTER_DURATION, easing = FastOutSlowInEasing)
        )
    }

    val popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
        fadeOut(
            animationSpec = tween(EXIT_DURATION, easing = FastOutSlowInEasing)
        ) + scaleOut(
            targetScale = 0.95f,
            animationSpec = tween(EXIT_DURATION, easing = FastOutSlowInEasing)
        )
    }
}
