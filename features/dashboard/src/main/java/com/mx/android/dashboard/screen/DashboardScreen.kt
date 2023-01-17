package com.mx.android.dashboard.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mx.android.common.base.ShareViewModel
import com.mx.android.dashboard.navigation.BottomNavRoute
import com.mx.android.dashboard.navigation.InitNavGraph
import com.mx.android.ui.components.topAppBars.TopBarItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val shareViewModel = hiltViewModel<ShareViewModel>()
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopBar(shareViewModel)
        },
        bottomBar = {
            NavBar(navController)
        }

    ) { innerPadding ->
        InitNavGraph(navController = navController, Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(shareViewModel: ShareViewModel) {
    var showMenu by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(
                "HeroApp",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "hero test"
                )
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(
                    text = {
                        TopBarItem("Tema del dispositivo", Icons.Filled.Contrast)
                    },
                    onClick = {
                        shareViewModel.changeThemeMode()
                        showMenu = false
                    })
                DropdownMenuItem(
                    text = {
                        TopBarItem("Compartir app", Icons.Filled.Share)
                    },
                    onClick = { /*TODO*/ }
                )
            }
        }
    )
}



@Composable
fun NavBar(navController: NavHostController) {
    val items = listOf(
        BottomNavRoute.Home,
        BottomNavRoute.Favorite,
        BottomNavRoute.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            NavBarItem(
                screen = item,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.NavBarItem(
    screen: BottomNavRoute,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        icon = { Icon(imageVector = screen.icon, contentDescription = "Navigation icon") },
        label = { Text(text = stringResource(screen.title)) },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}