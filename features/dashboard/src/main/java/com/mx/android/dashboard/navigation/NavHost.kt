package com.mx.android.dashboard.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mx.android.heros.screens.HeroScreen

@Composable
fun InitNavGraph(navController: NavHostController, modifier: Modifier) {

    NavHost(
        navController = navController,
        startDestination = BottomNavRoute.Home.route,
        modifier = modifier
    ) {

        composable(route = BottomNavRoute.Home.route) {
            HeroScreen()
        }
        composable(route = BottomNavRoute.Favorite.route) {
            HeroScreen() //TODO COMPOSE FAVORITES
        }
        composable(route = BottomNavRoute.Profile.route) {
            HeroScreen() //TODO COMPOSE PROFILE
        }
    }
}