package com.mx.android.dashboard.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.mx.android.dashboard.R


sealed class BottomNavRoute(
    val route: String,
    @StringRes
    val title: Int,
    val icon:  ImageVector,
) {
    object Home: BottomNavRoute(
        route = "home",
        title = R.string.home_item,
        icon = Icons.Default.Home
    )
    object Favorite: BottomNavRoute(
        route = "favorite",
        title = R.string.favorite_item,
        icon = Icons.Default.Favorite
    )
    object Profile: BottomNavRoute(
        route = "profile",
        title = R.string.profile_item,
        icon = Icons.Default.Person
    )
}
