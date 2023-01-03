package com.arwani.ahmad.schotersnews.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arwani.ahmad.schotersnews.navigation.Screen
import com.arwani.ahmad.schotersnews.ui.component.BottomBar
import com.arwani.ahmad.schotersnews.ui.home.HomeScreen
import com.arwani.ahmad.schotersnews.ui.profile.ProfileScreen

@Composable
fun JetNewsApp(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Favorite.route) {
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}