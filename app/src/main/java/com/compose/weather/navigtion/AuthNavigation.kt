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
fun AuthNavigation(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(
        navController = navController,
        startDestination = Route.AuthNav.Splash.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(
            route = Route.AuthNav.Splash.route
        ) {
            ComponentSplashScreen(navigateToLogin = {
                navController.navigate(it) {
                    // clear stack till Splash screen
                    popUpTo(Route.AuthNav.Splash.route) {
                        inclusive = true
                    }
                }
            }, navigateToHome = {
                navController.navigate(it) {
                    // clear stack till Splash screen
                    popUpTo(Route.AuthNav.Splash.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            route = Route.AuthNav.Login.route,
        ) {
            ComponentLoginScreen(navigateToDashboard = {
                navController.navigate(it) {
                    // clear stack till Login screen as Splash is already cleared
                    popUpTo(Route.AuthNav.Login.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            route = Route.AuthNav.Dashboard.route,
            arguments = Route.AuthNav.Dashboard.argumentsList
        ) {
            it.arguments?.apply {
                val userName = getStringOrThrowException(Route.AuthNav.Dashboard.USER_NAME)
                ComponentDashboardScreen(userName, logout = {
                    // Clear the current session
                    SharedPrefsManager.clearSession()

                    // Navigate to splash screen and clear all the back stack
                    navController.navigate(Route.AuthNav.Splash.route) {
                        navController.backQueue.clear()
                    }
                })
            }
        }
    }
}