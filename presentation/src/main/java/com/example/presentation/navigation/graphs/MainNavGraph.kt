package com.example.presentation.navigation.graphs

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.presentation.navigation.component.NavigationBar
import com.example.presentation.navigation.destinations.CharacterDetailDestination
import com.example.presentation.navigation.destinations.CharactersDestination
import com.example.presentation.navigation.destinations.EpisodesDesination
import com.example.presentation.navigation.destinations.LocationsDestination
import com.example.presentation.navigation.extension.linkedComposable
import com.example.presentation.navigation.menu.BaseDestination
import com.example.presentation.screen.characters.CharactersScreen
import com.example.presentation.screen.episodes.EpisodesScreen
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
                CharactersScreen(
                    onRegisterScrollToTop = { scrollTop ->
                        onHomeClick = scrollTop
                    },
                    onNavigateToDetail = { character ->
                        rootNavController.navigate(CharacterDetailDestination(character.id))
                    }
                )
            }

            linkedComposable<LocationsDestination> { LocationsScreen() }
            linkedComposable<EpisodesDesination> { EpisodesScreen() }
        }
    }
}