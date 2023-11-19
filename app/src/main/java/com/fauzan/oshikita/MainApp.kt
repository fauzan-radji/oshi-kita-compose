package com.fauzan.oshikita

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fauzan.oshikita.model.NavigationItem
import com.fauzan.oshikita.navigation.Screen
import com.fauzan.oshikita.ui.component.BottomBar
import com.fauzan.oshikita.ui.screen.AboutScreen
import com.fauzan.oshikita.ui.screen.DetailScreen
import com.fauzan.oshikita.ui.screen.FavoriteScreen
import com.fauzan.oshikita.ui.screen.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navigationItems = listOf(
        NavigationItem(
            title = stringResource(id = R.string.menu_home),
            icon = Icons.Default.Home,
            screen = Screen.Home
        ),
        NavigationItem(
            title = stringResource(id = R.string.menu_favorite),
            icon = Icons.Default.Favorite,
            screen = Screen.Favorite
        ),
        NavigationItem(
            title = stringResource(id = R.string.menu_about),
            icon = Icons.Default.Person,
            screen = Screen.About
        )
    )

    Scaffold(
        bottomBar = {
            if(currentRoute != Screen.Detail.route) {
                BottomBar(
                    navController = navController,
                    navigationItems = navigationItems
                )
            }
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