package com.example.jetpackcomposelesson.core.common

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.get
import androidx.navigation.navDeepLink
import com.example.jetpackcomposelesson.core.menu.BaseDestination
import com.stefanoq21.material3.navigation.BottomSheetNavigator
import com.stefanoq21.material3.navigation.BottomSheetNavigatorDestinationBuilder
import kotlin.collections.forEach
import kotlin.reflect.KType


fun <T> NavController.popBackStackWithResult(key: String, result: T) {
    previousBackStackEntry?.savedStateHandle?.set(key, result)
    popBackStack()
}

@Composable
fun <T> NavBackStackEntry.observeResult(key: String): State<T?> {
    return savedStateHandle.getStateFlow<T?>(key, null).collectAsState()
}

inline fun <reified T> NavGraphBuilder.linkedComposable(
    noinline enterTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?)? =
        null,
    noinline exitTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?)? =
        null,
    noinline popEnterTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?)? =
        enterTransition,
    noinline popExitTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?)? =
        exitTransition,
    noinline sizeTransform:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    SizeTransform?)? =
        null,
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) where T : BaseDestination {
    val destination = T::class.objectInstance as? BaseDestination
        ?: error("T must be an object implementing Destination")

    composable<T>(
        deepLinks = listOf(
            navDeepLink {
                uriPattern = destination.deepLink
            }
        ),
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        sizeTransform = sizeTransform,
        content = content
    )
}

inline fun <reified T : BaseDestination> NavGraphBuilder.linkedDialog(
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
    dialogProperties: DialogProperties = DialogProperties(),
    noinline content: @Composable (NavBackStackEntry) -> Unit
) {
    val destination = T::class.objectInstance as? BaseDestination
        ?: error("T must be an object implementing Destination")

    dialog(
        T::class,
        typeMap,
        deepLinks = listOf(
            navDeepLink {
                uriPattern = destination.deepLink
            }
        ),
        dialogProperties, content,
    )
}

inline fun <reified T : BaseDestination> NavGraphBuilder.linkedBottomSheet(
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
    noinline content: @Composable ColumnScope.(backstackEntry: NavBackStackEntry) -> Unit
) {
    val destination = T::class.objectInstance as? BaseDestination
        ?: error("T must be an object implementing Destination")

    destination(
        BottomSheetNavigatorDestinationBuilder(
            provider[BottomSheetNavigator::class],
            T::class,
            typeMap,
            content
        ).apply {
            deepLink(destination.deepLink)
        }
    )
}
