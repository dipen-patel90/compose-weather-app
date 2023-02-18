package com.compose.weather.navigtion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.weather.common.getStringOrThrowException
import com.compose.weather.view.ComponentLoginScreen
import com.compose.weather.view.ComponentSplashScreen

@Composable
fun ComponentNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Route.Splash.route) {
        composable(route = Route.Splash.route) {
            ComponentSplashScreen(navController)
        }
        composable(
            route = Route.Login.route,
            arguments = Route.Login.argumentsList
        ) {
            it.arguments?.apply {
                val loginId = getInt(Route.Login.LOGIN_ID)
                val userName = getStringOrThrowException(Route.Login.USER_NAME)

                val lastname = getString(Route.Login.LAST_NAME)

                ComponentLoginScreen(navController, loginId, userName, lastname)
            }
        }
    }
}