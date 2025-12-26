package com.example.jetpackcomposelesson.core.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetpackcomposelesson.core.navigation.destinations.CharactersDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.NavbarItem
import com.example.jetpackcomposelesson.core.navigation.destinations.NotificationDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.LocationsDestination


@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    onItemClick: (NavbarItem) -> Unit
) {
    val destinations = listOf<NavbarItem>(
        CharactersDestination,
        LocationsDestination,
        NotificationDestination
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    NavigationBar(modifier = modifier) {
        destinations.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any {
                it.hasRoute(screen::class)
            } == true

            NavigationBarItem(
                selected = isSelected,
                label = { Text(text = screen.label) },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.label
                    )
                },
                onClick = {
                    onItemClick(screen)
                    navController.navigate(screen) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}