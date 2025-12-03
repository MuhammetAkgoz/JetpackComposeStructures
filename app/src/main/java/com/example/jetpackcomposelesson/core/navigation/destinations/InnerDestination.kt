package com.example.jetpackcomposelesson.core.navigation.destinations
import com.example.jetpackcomposelesson.core.menu.MenuKey
import kotlinx.serialization.Serializable

/* Inner Root */
@Serializable data class ProfileDestination(val name: String, val age: Int) : MenuKey
@Serializable data object DetailDestination : MenuKey
