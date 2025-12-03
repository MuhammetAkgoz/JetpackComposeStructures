package com.example.jetpackcomposelesson.core.navigation.graphs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.toRoute
import com.example.jetpackcomposelesson.core.component.BaseBottomSheet
import com.example.jetpackcomposelesson.core.component.BaseAlertDialog
import com.example.jetpackcomposelesson.core.component.BaseDialog
import com.example.jetpackcomposelesson.core.menu.MenuKey
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
import com.stefanoq21.material3.navigation.bottomSheet


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    bottomSheetNavigator: BottomSheetNavigator,
    startDestination: MenuKey,
) {

    ModalBottomSheetLayout(
        modifier = modifier,
        bottomSheetNavigator = bottomSheetNavigator,
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable<MainDestination> {
                MainNavGraph(
                    rootNavController = navController,
                    startDestination = SearchDestination
                )
            }


            composable<ProfileDestination> { backStackEntry ->
                val args = backStackEntry.toRoute<ProfileDestination>()
                ProfileScreen(
                    onBack = { result ->
                        navController.popBackStack()
                    },
                )
            }

            composable<DetailDestination> {
                DetailScreen(
                    onBottomSheet = {
                        navController.navigate(BottomSheetDestination)
                    },
                    onAlertDialog = {
                        navController.navigate(AlertDialogDestination)
                    },
                    onDialog = {
                        navController.navigate(DialogDestination)
                    }

                )
            }

            bottomSheet<BottomSheetDestination> { BaseBottomSheet { print("bottomsheet") } }

            dialog<AlertDialogDestination> {
                BaseAlertDialog("Say Hello", onConfirm = { print("confirmed") }, onDismiss = {
                    navController.popBackStack()
                })
            }

            dialog<DialogDestination> {
                BaseDialog(onConfirm = { print("confirmed") }, onDismiss = {
                    navController.popBackStack()
                })
            }

        }

    }


}