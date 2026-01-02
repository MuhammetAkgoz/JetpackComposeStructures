package com.example.jetpackcomposelesson.core.navigation.destinations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.People
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpackcomposelesson.core.menu.BaseDestination
import com.example.jetpackcomposelesson.core.menu.MenuKey
import com.example.jetpackcomposelesson.core.menu.ScreenName
import kotlinx.serialization.Serializable

sealed interface NavbarItem {
    val icon: ImageVector
    val label: String
}

@Serializable
data object MainDestination : BaseDestination(
    key = MenuKey.MAIN,
    name = ScreenName.MAIN
)

@Serializable
data object CharactersDestination : BaseDestination(
    key = MenuKey.CHARACTERS,
    name = ScreenName.CHARACTERS
), NavbarItem {
    override val icon = Icons.Default.People
    override val label = "Characters"
}

@Serializable
data object EpisodesDesination : BaseDestination(
    key = MenuKey.EPISODES,
    name = ScreenName.EPISODES
), NavbarItem {
    override val icon = Icons.Default.Movie
    override val label = "Episodes"
}

@Serializable
data object LocationsDestination : BaseDestination(
    key = MenuKey.LOCATIONS,
    name = ScreenName.LOCATIONS
), NavbarItem {
    override val icon = Icons.Default.LocationOn
    override val label = "Locations"
}

@Serializable
data object ProfileDestination : BaseDestination(
    key = MenuKey.PROFILE,
    name = ScreenName.PROFILE
)

@Serializable
data object DetailDestination : BaseDestination(
    key = MenuKey.DETAIL,
    name = ScreenName.DETAIL
)

@Serializable
data object BottomSheetDestination : BaseDestination(
    key = MenuKey.BOTTOM_SHEET,
    name = ScreenName.BOTTOM_SHEET
)

@Serializable
data object AlertDialogDestination : BaseDestination(
    key = MenuKey.ALERT_DIALOG,
    name = ScreenName.ALERT_DIALOG
)

@Serializable
data object DialogDestination : BaseDestination(
    key = MenuKey.DIALOG,
    name = ScreenName.DIALOG
)