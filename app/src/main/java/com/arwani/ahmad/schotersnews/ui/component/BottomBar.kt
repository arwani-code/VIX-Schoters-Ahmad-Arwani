package com.arwani.ahmad.schotersnews.ui.component

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arwani.ahmad.schotersnews.navigation.NavigationItem
import com.arwani.ahmad.schotersnews.navigation.Screen
import com.arwani.ahmad.schotersnews.ui.theme.Black100
import com.arwani.ahmad.schotersnews.ui.theme.Blue50
import com.arwani.ahmad.schotersnews.ui.theme.Grey100


@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    if (currentRoute != Screen.splash.route) {
        BottomNavigation(
            modifier = modifier.padding(top = 8.dp),
        ) {
            val navigationItems = listOf(
                NavigationItem(title = "Home", icon = Icons.Default.Home, screen = Screen.Home),
                NavigationItem(
                    title = "Profile", icon = Icons.Default.AccountCircle, screen = Screen.Profile
                )
            )
            BottomNavigation(
                backgroundColor = Black100,
                contentColor = Blue50,
            ) {
                navigationItems.map { item ->
                    BottomNavigationItem(selected = currentRoute == item.screen.route, onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }, label = { Text(item.title) },
                        unselectedContentColor = Grey100,
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = item.title)
                        })
                }
            }
        }
    }

}