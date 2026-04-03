package com.example.jetpackcomposelesson.core.navigation.graphs

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.presentation.navigation.component.BaseAlertDialog
import com.example.presentation.navigation.component.BaseBottomSheet
import com.example.presentation.navigation.component.BaseDialog
import com.example.presentation.navigation.destinations.AlertDialogDestination
import com.example.presentation.navigation.destinations.BottomSheetDestination
import com.example.presentation.navigation.destinations.CharacterDetailDestination
import com.example.presentation.navigation.destinations.CharactersDestination
import com.example.presentation.navigation.destinations.DetailDestination
import com.example.presentation.navigation.destinations.DialogDestination
import com.example.presentation.navigation.destinations.MainDestination
import com.example.presentation.navigation.destinations.ProfileDestination
import com.example.presentation.navigation.extension.linkedBottomSheet
import com.example.presentation.navigation.extension.linkedComposable
import com.example.presentation.navigation.extension.linkedDialog
import com.example.presentation.navigation.graphs.MainNavGraph
import com.example.presentation.navigation.menu.BaseDestination
import com.example.presentation.screen.characterdetail.CharacterDetailScreen
import com.example.presentation.screen.detail.DetailScreen
import com.example.presentation.screen.profile.ProfileScreen
import com.stefanoq21.material3.navigation.BottomSheetNavigator
import com.stefanoq21.material3.navigation.ModalBottomSheetLayout


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
                    startDestination = CharactersDestination
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

            composable<CharacterDetailDestination> {
                CharacterDetailScreen(
                    onBack = { navController.popBackStack() }
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