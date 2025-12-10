package com.example.jetpackcomposelesson.core.navigation.graphs

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.toRoute
import com.example.jetpackcomposelesson.core.component.BaseBottomSheet
import com.example.jetpackcomposelesson.core.component.BaseAlertDialog
import com.example.jetpackcomposelesson.core.component.BaseDialog
import com.example.jetpackcomposelesson.core.navigation.destinations.BottomSheetDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.DetailDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.AlertDialogDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.DialogDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.MainDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.ProfileDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.SearchDestination
import com.example.jetpackcomposelesson.feature.ui.screen.detail.DetailScreen
import com.example.jetpackcomposelesson.feature.ui.screen.profile.ProfileScreen
import com.stefanoq21.material3.navigation.BottomSheetNavigator
import com.stefanoq21.material3.navigation.ModalBottomSheetLayout
import androidx.core.net.toUri
import com.example.jetpackcomposelesson.core.extension.linkedBottomSheet
import com.example.jetpackcomposelesson.core.extension.linkedComposable
import com.example.jetpackcomposelesson.core.extension.linkedDialog
import com.example.jetpackcomposelesson.core.menu.BaseDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.HomeDestination


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    bottomSheetNavigator: BottomSheetNavigator,
    startDestination: BaseDestination,
) {

    ModalBottomSheetLayout(
        modifier = modifier,
        bottomSheetNavigator = bottomSheetNavigator,
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    tween(400)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    tween(400)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(400)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(400)
                )
            }
        ) {
            linkedComposable<MainDestination> {
                MainNavGraph(
                    rootNavController = navController,
                    startDestination = HomeDestination
                )
            }

            linkedComposable<ProfileDestination> { backStackEntry ->
                val args = backStackEntry.toRoute<ProfileDestination>()
                ProfileScreen(
                    onBack = { result ->
                        navController.popBackStack()
                    },
                )
            }

            linkedComposable<DetailDestination> {
                DetailScreen(
                    onBottomSheet = {
                        navController.navigate(BottomSheetDestination.deepLink.toUri())
                    },
                    onAlertDialog = {
                        navController.navigate(AlertDialogDestination.deepLink.toUri())
                    },
                    onDialog = {
                        navController.navigate(DialogDestination.deepLink.toUri())
                    },
                    onNavigateProfile = {
                        navController.navigate(ProfileDestination.deepLink.toUri())
                    }
                )
            }

            linkedBottomSheet<BottomSheetDestination> { BaseBottomSheet { print("bottomsheet") } }

            linkedDialog<AlertDialogDestination> {
                BaseAlertDialog("Say Hello", onConfirm = { print("confirmed") }, onDismiss = {
                    navController.popBackStack()
                })
            }

            linkedDialog<DialogDestination> {
                BaseDialog(onConfirm = { print("confirmed") }, onDismiss = {
                    navController.popBackStack()
                })
            }
        }
    }
}