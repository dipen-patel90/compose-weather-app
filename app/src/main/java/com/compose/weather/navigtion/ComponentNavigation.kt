package com.compose.weather.navigtion

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.weather.common.getStringOrThrowException
import com.compose.weather.preferences.SharedPrefsManager
import com.compose.weather.view.dashboard.ComponentDashboardScreen
import com.compose.weather.view.login.ComponentLoginScreen
import com.compose.weather.view.splash.ComponentSplashScreen

@Composable
fun ComponentNavigation(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(
        navController = navController,
        startDestination = Route.Splash.route,
        modifier = Modifier.padding(paddingValues)
    ) {
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
            ComponentLoginScreen(navigateToDashboard = {
                navController.navigate(it) {
                    // clear stack till Login screen as Splash is already cleared
                    popUpTo(Route.Login.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            route = Route.Dashboard.route,
            arguments = Route.Dashboard.argumentsList
        ) {
            it.arguments?.apply {
                val userName = getStringOrThrowException(Route.Dashboard.USER_NAME)
                ComponentDashboardScreen(userName, logout = {
                    SharedPrefsManager.clearSession()

                    navController.navigate(Route.Splash.route) {
                        // clear stack till Splash screen
                        popUpTo(Route.Splash.route) {
                            inclusive = true
                        }
                    }
                })
            }
        }
    }
}