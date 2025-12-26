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
import com.example.jetpackcomposelesson.core.navigation.destinations.CharactersDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.NotificationDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.LocationsDestination
import com.example.presentation.screen.characters.CharactersScreen
import com.example.presentation.screen.notification.NotificationScreen
import com.example.presentation.screen.locations.LocationsScreen


@Composable
fun MainNavGraph(rootNavController: NavController, startDestination: BaseDestination) {
    val bottomBarNavController = rememberNavController()
    var onHomeClick: (() -> Unit)? = null
    Scaffold(
        bottomBar = {
            NavigationBar(navController = bottomBarNavController) {
                if (it is CharactersDestination) {
                    onHomeClick?.invoke()
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomBarNavController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            linkedComposable<CharactersDestination> {
                CharactersScreen{ scrollTop ->
                    onHomeClick = scrollTop
                }
            }

            linkedComposable<LocationsDestination> { LocationsScreen() }
            linkedComposable<NotificationDestination> { NotificationScreen() }
        }
    }
}