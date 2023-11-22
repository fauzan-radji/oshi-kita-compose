package com.fauzan.oshikita

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
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
import com.fauzan.oshikita.ui.component.TopBar
import com.fauzan.oshikita.ui.screen.about.AboutScreen
import com.fauzan.oshikita.ui.screen.detail.DetailScreen
import com.fauzan.oshikita.ui.screen.detail.DetailViewModel
import com.fauzan.oshikita.ui.screen.favorite.FavoriteScreen
import com.fauzan.oshikita.ui.screen.favorite.FavoriteViewModel
import com.fauzan.oshikita.ui.screen.home.HomeScreen
import com.fauzan.oshikita.ui.screen.home.HomeViewModel

@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val repository = Injection.provideRepository()
    val homeViewModel: HomeViewModel = viewModel(factory = ViewModelFactory(repository))
    val favoriteViewModel: FavoriteViewModel = viewModel(factory = ViewModelFactory(repository))
    val detailViewModel: DetailViewModel = viewModel(factory = ViewModelFactory(repository))

    val navigationItems = listOf(
        NavigationItem(
            title = stringResource(id = R.string.menu_home),
            icon = Icons.Default.Home,
            screen = Screen.Home,
        ),
        NavigationItem(
            title = stringResource(id = R.string.menu_oshi),
            icon = Icons.Default.Favorite,
            screen = Screen.Favorite,
        ),
        NavigationItem(
            title = stringResource(id = R.string.menu_about),
            icon = Icons.Default.Person,
            screen = Screen.About
        )
    )

    Scaffold(
        topBar = {
            Box {
                TopBar(
                    leadingIcon = {
                        if(currentRoute == Screen.Detail.route) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.navigate_back)
                            )
                        }
                    },
                    leadingIconOnClick = {
                        if(currentRoute == Screen.Detail.route) {
                            navController.navigateUp()
                        }
                    }
                ) {
                    Text(
                        text =
                        when (currentRoute) {
                            Screen.Home.route -> stringResource(id = R.string.menu_home)
                            Screen.Detail.route -> stringResource(id = R.string.menu_detail)
                            Screen.Favorite.route -> stringResource(id = R.string.menu_oshi)
                            Screen.About.route -> stringResource(id = R.string.menu_about)
                            else -> stringResource(id = R.string.app_name)
                        },
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        },
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
                detailViewModel.member.collectAsState().value.let {it?.let { member ->
                    FloatingActionButton(onClick = {
                        detailViewModel.setOshi(member.id, !member.isOshi)
                    }) {
                        Icon(
                            imageVector = if (member.isOshi) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(id = R.string.add_to_my_oshi)
                        )
                    }
                }}
            }
        },
        modifier = modifier,
    ) { innerPadding ->
        val navigateToDetail = { id: Int ->
            navController.navigate(Screen.Detail.createRoute(id))
        }

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                homeViewModel.getAllMember()
                HomeScreen(
                    navigateToDetail = navigateToDetail,
                    viewModel = homeViewModel
                )
            }

            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument(NavArg.MEMBER_ID.key) { type = NavType.IntType })
            ) {
                detailViewModel.getMemberById(it.arguments?.getInt(NavArg.MEMBER_ID.key) ?: -1)
                DetailScreen(viewModel = detailViewModel)
            }

            composable(Screen.Favorite.route) {
                favoriteViewModel.getOshi()
                FavoriteScreen(
                    navigateToDetail = navigateToDetail,
                    viewModel = favoriteViewModel
                )
            }

            composable(Screen.About.route) {
                AboutScreen()
            }
        }
    }
}