package com.fauzan.oshikita

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.fauzan.oshikita.ui.component.SearchBar
import com.fauzan.oshikita.ui.component.TopBar
import com.fauzan.oshikita.ui.component.TopBarTitle
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

    val homeQuery by homeViewModel.query
    val favoriteQuery by favoriteViewModel.query
    val detailMember by detailViewModel.member.collectAsState()

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

    val mContext = LocalContext.current
    Scaffold(
        topBar = {
            TopBar(
                leadingIcon = if(currentRoute == Screen.Detail.route) ({
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.navigate_back)
                    )
                }) else null,
                leadingIconOnClick = if(currentRoute == Screen.Detail.route) ({
                    navController.popBackStack()
                }) else ({}),

                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = stringResource(R.string.settings)
                    )
                },
                trailingIconOnClick = {
                    mContext.startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                },
            ) {
                when (currentRoute) {
                    Screen.Home.route -> SearchBar(
                        query = homeQuery,
                        onQueryChange = homeViewModel::setQuery,
                        backgroundColor = Color.Transparent,
                        cursorColor = MaterialTheme.colorScheme.onPrimary
                    )

                    Screen.Favorite.route -> SearchBar(
                        query = favoriteQuery,
                        onQueryChange = favoriteViewModel::setQuery,
                        backgroundColor = Color.Transparent,
                        cursorColor = MaterialTheme.colorScheme.onPrimary
                    )

                    else -> TopBarTitle(
                        text = when (currentRoute) {
                            Screen.Detail.route -> detailMember
                                ?.nicknames
                                ?.first() ?: stringResource(id = R.string.app_name)
                            Screen.About.route -> stringResource(id = R.string.menu_about)
                            else -> stringResource(id = R.string.app_name)
                        }
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
                detailMember.let {it?.let { member ->
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