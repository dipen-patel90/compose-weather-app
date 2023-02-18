package com.compose.weather.navigtion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.weather.common.getStringOrThrowException
import com.compose.weather.view.ComponentHomeScreen
import com.compose.weather.view.ComponentLoginScreen
import com.compose.weather.view.ComponentSplashScreen

@Composable
fun ComponentNavigation(navController: NavHostController) {

    NavHost(navController, startDestination = Route.Splash.route) {
        composable(
            route = Route.Splash.route
        ) {
            ComponentSplashScreen(navigateToLogin = {
                navController.navigate(it)
            })
        }
        composable(
            route = Route.Login.route,
        ) {
            it.arguments?.apply {
                ComponentLoginScreen(navigateToHome = {
                    navController.navigate(it)
                })
            }
        }
        composable(
            route = Route.Home.route,
            arguments = Route.Home.argumentsList
        ) {
            it.arguments?.apply {
                val userName = getStringOrThrowException(Route.Home.USER_NAME)
                ComponentHomeScreen(userName)
            }
        }
    }
}