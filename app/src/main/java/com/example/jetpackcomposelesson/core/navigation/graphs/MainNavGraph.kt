package com.example.jetpackcomposelesson.core.navigation.graphs

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposelesson.core.component.NavigationBar
import com.example.jetpackcomposelesson.core.extension.linkedComposable
import com.example.jetpackcomposelesson.core.menu.BaseDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.DetailDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.HomeDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.NotificationDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.SearchDestination
import com.example.jetpackcomposelesson.feature.ui.screen.home.HomeScreen
import com.example.jetpackcomposelesson.feature.ui.screen.notification.NotificationScreen
import com.example.jetpackcomposelesson.feature.ui.screen.search.SearchScreen


@Composable
fun MainNavGraph(rootNavController: NavController, startDestination: BaseDestination) {
    val bottomBarNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(navController = bottomBarNavController)
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomBarNavController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            linkedComposable<HomeDestination> {
                HomeScreen(
                    onNavigateToProfile = {
                        rootNavController.navigate( DetailDestination)
                    },
                    title = "Title"
                )
            }

            linkedComposable<SearchDestination> { SearchScreen() }
            linkedComposable<NotificationDestination> { NotificationScreen() }
        }
    }
}