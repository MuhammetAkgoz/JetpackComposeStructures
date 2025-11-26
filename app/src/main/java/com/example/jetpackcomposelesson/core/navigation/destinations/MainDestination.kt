package com.example.jetpackcomposelesson.core.navigation.destinations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpackcomposelesson.core.menu.MenuKey
import kotlinx.serialization.Serializable

sealed interface NavbarItem {
    val icon: ImageVector
    val label: String
}

@Serializable
data object MainDestination : MenuKey

@Serializable
data object HomeDestination : MenuKey, NavbarItem {
    override val icon = Icons.Default.Home
    override val label = "Home"
}

@Serializable
data object NotificationDestination : MenuKey , NavbarItem {
    override val icon = Icons.Default.Notifications
    override val label = "Notification"
}

@Serializable
data object SearchDestination : MenuKey ,  NavbarItem {
    override val icon = Icons.Default.Search
    override val label = "Search"
}