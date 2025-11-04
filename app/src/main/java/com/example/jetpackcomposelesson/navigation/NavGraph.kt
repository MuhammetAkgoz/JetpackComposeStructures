package com.example.jetpackcomposelesson.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.jetpackcomposelesson.ui.screen.home.HomeScreen
import com.example.jetpackcomposelesson.ui.screen.profile.ProfileScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.HOME_ROUTE
){

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Destinations.HOME_ROUTE){
            HomeScreen()
        }

        composable(route = Destinations.PROFILE_ROUTE){
            ProfileScreen()
        }
    }

}