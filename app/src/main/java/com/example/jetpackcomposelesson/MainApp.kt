package com.example.jetpackcomposelesson

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposelesson.core.navigation.NavGraph


@Composable
fun MainApp(modifier: Modifier = Modifier,){
    val navController = rememberNavController()
    NavGraph(modifier = modifier, navController = navController)
}