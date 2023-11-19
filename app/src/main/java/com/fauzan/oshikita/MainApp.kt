package com.fauzan.oshikita

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fauzan.oshikita.navigation.Screen
import com.fauzan.oshikita.ui.component.BottomBar
import com.fauzan.oshikita.ui.screen.AboutScreen
import com.fauzan.oshikita.ui.screen.DetailScreen
import com.fauzan.oshikita.ui.screen.FavoriteScreen
import com.fauzan.oshikita.ui.screen.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if(currentRoute != Screen.Detail.route)
                BottomBar(navController = navController)
        },
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }

            composable(Screen.Detail.route) {
                DetailScreen()
            }

            composable(Screen.Favorite.route) {
                FavoriteScreen()
            }

            composable(Screen.About.route) {
                AboutScreen()
            }
        }
    }
}