package com.fauzan.oshikita.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Detail: Screen("detail")
    object Favorite: Screen("favorite")
    object About: Screen("about")
}
