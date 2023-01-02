package com.arwani.ahmad.schotersnews.navigation

sealed class Screen(val route: String){
    object Home : Screen("Home")
    object Favorite : Screen("Favorite")
    object Profile : Screen("Profile")
}