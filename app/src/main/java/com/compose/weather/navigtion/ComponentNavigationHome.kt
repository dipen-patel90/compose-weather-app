package com.compose.weather.navigtion

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.weather.view.about.ComponentAboutScreen
import com.compose.weather.view.home.ComponentHomeScreen
import com.compose.weather.view.profile.ComponentProfileScreen
import com.compose.weather.view.settings.ComponentSettingsScreen

@Composable
fun ComponentNavigationHome(
    navController: NavHostController,
    paddingValues: PaddingValues,
    username: String,
    logout: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(
            route = Route.Home.route
        ) {
            ComponentHomeScreen(username)
        }
        composable(
            route = Route.Settings.route,
        ) {
            ComponentSettingsScreen {
                navController.navigate(Route.Home.route) {
                    popUpTo(Route.Home.route) {
                        inclusive = true
                        logout.invoke()
                    }
                }
            }
        }

        composable(
            route = Route.Profile.route,
        ) {
            ComponentProfileScreen()
        }

        composable(
            route = Route.About.route,
        ) {
            ComponentAboutScreen()
        }
    }
}