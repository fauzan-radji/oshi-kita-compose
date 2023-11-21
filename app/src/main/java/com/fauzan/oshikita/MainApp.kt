package com.fauzan.oshikita

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fauzan.oshikita.di.Injection
import com.fauzan.oshikita.model.NavigationItem
import com.fauzan.oshikita.navigation.NavArg
import com.fauzan.oshikita.navigation.Screen
import com.fauzan.oshikita.ui.ViewModelFactory
import com.fauzan.oshikita.ui.component.BottomBar
import com.fauzan.oshikita.ui.screen.about.AboutScreen
import com.fauzan.oshikita.ui.screen.detail.DetailScreen
import com.fauzan.oshikita.ui.screen.detail.DetailViewModel
import com.fauzan.oshikita.ui.screen.favorite.FavoriteScreen
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
        floatingActionButton = {
            if (currentRoute == Screen.Detail.route) {
                val detailViewModel: DetailViewModel = viewModel(
                    factory = ViewModelFactory(Injection.provideRepository())
                )
                val memberId = navBackStackEntry?.arguments?.getInt(NavArg.MEMBER_ID.key) ?: -1
                detailViewModel.oshiState(memberId).collectAsState().value.let { oshiState ->
                        FloatingActionButton(onClick = {
                            detailViewModel.setOshi(memberId, !oshiState)
                        }) {
                            Icon(
                                imageVector = if (oshiState) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = stringResource(id = R.string.add_to_my_oshi)
                            )
                        }
                }
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
                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    }
                )
            }

            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument(NavArg.MEMBER_ID.key) { type = NavType.IntType })
            ) {
                val memberId = it.arguments?.getInt(NavArg.MEMBER_ID.key) ?: -1
                DetailScreen(memberId = memberId)
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