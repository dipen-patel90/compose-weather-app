package com.compose.weather.navigtion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.weather.common.getStringOrThrowException
import com.compose.weather.view.home.ComponentHomeScreen
import com.compose.weather.view.splash.ComponentSplashScreen
import com.compose.weather.view.login.ComponentLoginScreen

@Composable
fun ComponentNavigation(navController: NavHostController) {

    NavHost(navController, startDestination = Route.Splash.route) {
        composable(
            route = Route.Splash.route
        ) {
            ComponentSplashScreen(navigateToLogin = {
                navController.navigate(it) {
                    // clear stack till Splash screen
                    popUpTo(Route.Splash.route) {
                        inclusive = true
                    }
                }
            }, navigateToHome = {
                navController.navigate(it) {
                    // clear stack till Splash screen
                    popUpTo(Route.Splash.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            route = Route.Login.route,
        ) {
            ComponentLoginScreen(navigateToHome = {
                navController.navigate(it) {
                    // clear stack till Login screen as Splash is already cleared
                    popUpTo(Route.Login.route) {
                        inclusive = true
                    }
                }
            })
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