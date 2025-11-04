package com.example.jetpackcomposelesson

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposelesson.navigation.NavGraph


@Composable
fun MainApp(){
    val navController = rememberNavController()
    NavGraph(navController = navController)
}