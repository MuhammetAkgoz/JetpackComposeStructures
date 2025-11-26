package com.example.jetpackcomposelesson.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.jetpackcomposelesson.core.common.NavigationResult
import com.example.jetpackcomposelesson.core.common.observeResult
import com.example.jetpackcomposelesson.core.common.popBackStackWithResult
import com.example.jetpackcomposelesson.feature.ui.screen.home.HomeScreen
import com.example.jetpackcomposelesson.feature.ui.screen.profile.ProfileScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: HomeRoute = HomeRoute
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable<HomeRoute> { backStackEntry ->
            val result by backStackEntry.observeResult<String>(NavigationResult.PROFILE)

            HomeScreen(
                title = result,
                onNavigateToProfile = {
                    navController.navigate(ProfileRoute(name = "Muhammet", age = 25))
                }
            )
        }

        composable<ProfileRoute> { backStackEntry ->
            val args = backStackEntry.toRoute<ProfileRoute>()
            ProfileScreen(
                profileRoute = args,
                onBack = { result ->
                    navController.popBackStackWithResult(NavigationResult.PROFILE, result)
                },
            )
        }
    }

}