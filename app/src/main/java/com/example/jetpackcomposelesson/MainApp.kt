package com.example.jetpackcomposelesson

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposelesson.core.navigation.destinations.DetailDestination
import com.example.jetpackcomposelesson.core.navigation.destinations.MainDestination
import com.example.jetpackcomposelesson.core.navigation.graphs.AppNavGraph
import com.stefanoq21.material3.navigation.rememberBottomSheetNavigator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(modifier: Modifier = Modifier) {
    val bottomSheetNavigator = rememberBottomSheetNavigator(skipPartiallyExpanded = true)
    val navController = rememberNavController(bottomSheetNavigator)
    AppNavGraph(
        modifier = modifier,
        navController = navController,
        bottomSheetNavigator = bottomSheetNavigator,
        startDestination = DetailDestination,
    )
}