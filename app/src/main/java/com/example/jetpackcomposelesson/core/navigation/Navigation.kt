package com.example.jetpackcomposelesson.core.navigation

import kotlinx.serialization.Serializable


/* Main Root */
@Serializable data object HomeRoute
@Serializable data object DetailRoute
@Serializable data object SearchRoute

/* Inner Root */
@Serializable data class ProfileRoute(val name: String, val age: Int)
@Serializable data object NotificationRoute