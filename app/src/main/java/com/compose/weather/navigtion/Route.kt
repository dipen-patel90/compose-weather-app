package com.compose.weather.navigtion

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Route(val route: String) {
    object AuthNav {
        object Splash : Route("splash")

        object Login : Route("login") {
            fun createRoute() = "login"
        }

        object Dashboard : Route("dashboard/{username}") {
            const val USER_NAME = "username"

            val argumentsList = listOf(
                navArgument(USER_NAME) { type = NavType.StringType },
            )

            fun createRoute(userName: String) =
                "dashboard/$userName"
        }
    }

    object HomeNav {
        object Home : Route("home")

        object Settings : Route("settings")

        object Profile : Route("profile")

        object About : Route("about")
    }
}