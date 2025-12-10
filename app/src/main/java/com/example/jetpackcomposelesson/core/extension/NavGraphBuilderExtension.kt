package com.example.jetpackcomposelesson.core.extension

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.get
import androidx.navigation.navDeepLink
import com.example.jetpackcomposelesson.core.menu.BaseDestination
import com.stefanoq21.material3.navigation.BottomSheetNavigator
import com.stefanoq21.material3.navigation.BottomSheetNavigatorDestinationBuilder
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * Add the [Composable] to the [NavGraphBuilder]
 *
 * @param T route from a [KClass] for the destination
 * @param enterTransition callback to determine the destination's enter transition
 * @param exitTransition callback to determine the destination's exit transition
 * @param popEnterTransition callback to determine the destination's popEnter transition
 * @param popExitTransition callback to determine the destination's popExit transition
 * @param sizeTransform callback to determine the destination's sizeTransform.
 * @param content composable for the destination
 */
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


/**
 * Add the [Composable] to the [NavGraphBuilder] that will be hosted within a
 * [androidx.compose.ui.window.Dialog]. This is suitable only when this dialog represents a separate
 * screen in your app that needs its own lifecycle and saved state, independent of any other
 * destination in your navigation graph. For use cases such as `AlertDialog`, you should use those
 * APIs directly in the [composable] destination that wants to show that dialog.
 *
 * @param dialogProperties properties that should be passed to [androidx.compose.ui.window.Dialog].
 * @param content composable content for the destination that will be hosted within the Dialog
 */
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


/**
 * Add the [Composable] to the [NavGraphBuilder] that will be hosted within a
 * Bottom Sheet. This is suitable only when this dialog represents a separate
 * screen in your app that needs its own lifecycle and saved state, independent of any other
 * destination in your navigation graph. For use cases such as `BottomSheet`, you should use those
 * APIs directly in the [composable] destination that wants to show that sheet.
 *
 * @param content composable content for the destination that will be hosted within the Bottom Sheet
 */
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