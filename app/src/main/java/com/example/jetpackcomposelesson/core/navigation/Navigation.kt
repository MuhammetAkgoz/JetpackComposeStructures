package com.example.jetpackcomposelesson.core.navigation

import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data class ProfileRoute(val name: String, val age: Int)