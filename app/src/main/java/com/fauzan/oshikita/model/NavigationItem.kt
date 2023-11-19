package com.fauzan.oshikita.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.fauzan.oshikita.navigation.Screen

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)
