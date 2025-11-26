package com.example.jetpackcomposelesson.core.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.jetpackcomposelesson.core.menu.MenuKey
import com.example.jetpackcomposelesson.core.navigation.destinations.DetailDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.HomeDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.MainDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.NotificationDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.ProfileDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.SearchDestination
import com.example.jetpackcomposelesson.feature.ui.screen.detail.DetailScreen
import com.example.jetpackcomposelesson.feature.ui.screen.profile.ProfileScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: MenuKey,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable<MainDestination>  {
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
            DetailScreen()
        }
    }

}