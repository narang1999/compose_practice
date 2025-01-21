package com.example.composebasics.homescreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composebasics.homescreen.data.HomeDesination
import androidx.navigation.compose.composable
import com.example.composebasics.homescreen.FavoriteScreen
import com.example.composebasics.homescreen.HomeNewScreen
import com.example.composebasics.homescreen.HomeViewModel
import com.example.composebasics.homescreen.LatestScreen
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.Route
import org.koin.androidx.compose.koinViewModel
import org.koin.java.KoinJavaComponent.inject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreen(modifier: Modifier) {
    val navController: NavHostController = rememberNavController()
    val backStackEntry by
    navController.currentBackStackEntryAsState()
    val currentBackStack = backStackEntry?.destination?.route
    val coroutineScope = rememberCoroutineScope()
    val newDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)


    ModalNavigationDrawer(
        drawerState = newDrawerState,
        drawerContent = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(32.dp)
                ) {
                    Text(text = "Drawer Item 1", modifier = Modifier.padding(vertical = 8.dp))
                    Text(text = "Drawer Item 2", modifier = Modifier.padding(vertical = 8.dp))
                    Text(text = "Drawer Item 3", modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    ) {
        Scaffold(topBar = {
            TopBar { coroutineScope.launch { newDrawerState.open() } }

        },
            bottomBar = {
                BottomNavBar(
                    navController = navController,
                    currentBackStack ?: HomeDesination.Home.route
                )
            }) { innerPadding ->
            navigationGraph(navController = navController, modifier.padding(innerPadding),)
        }
    }
}

@Composable
fun TopBar(onNavigation: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "Movies")
        },
        navigationIcon = {
            IconButton(onClick = { onNavigation() }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        },
        backgroundColor = Color.White
    )
}

fun NavHostController.launchSingleTop(route: String) {
    this.navigate(route) {
        popUpTo(this@launchSingleTop.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

}

val bottomItem = listOf(HomeDesination.Home, HomeDesination.Latest, HomeDesination.Favorite)

@Composable
fun BottomNavBar(navController: NavHostController, currentRoute: String?) {
    NavigationBar {
        bottomItem.forEach { destination ->
            NavigationBarItem(
                selected = currentRoute == destination.route, // Correctly compare routes
                onClick = {
                    if (currentRoute != destination.route) { // Avoid unnecessary navigation
                        navController.navigate(destination.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = destination.icons,
                        contentDescription = destination.title
                    )
                },
                label = { Text(text = destination.title) },

                )
        }
    }
}

@Composable
fun navigationGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = HomeDesination.Home.route,
        modifier = modifier,

        ) {
        composable(route = HomeDesination.Home.route) {
            val myViewModel: HomeViewModel = koinViewModel()
            HomeNewScreen(myViewModel)
        }
        composable(route = HomeDesination.Latest.route) {
            LatestScreen()

        }
        composable(route = HomeDesination.Favorite.route) {
            FavoriteScreen()
        }
    }

}
