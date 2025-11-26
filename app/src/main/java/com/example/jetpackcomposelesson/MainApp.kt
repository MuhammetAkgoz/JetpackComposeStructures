package com.example.jetpackcomposelesson

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposelesson.core.navigation.destinations.DetailDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.MainDestination
import com.example.jetpackcomposelesson.core.navigation.graphs.AppNavGraph


@Composable
fun MainApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    AppNavGraph(
        modifier = modifier,
        navController = navController,
        startDestination = MainDestination
    )
}