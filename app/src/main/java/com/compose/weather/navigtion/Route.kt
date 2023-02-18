package com.compose.weather.navigtion

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Route(val route: String) {
    object Splash : Route("splash")

    object Login : Route("login") {
        fun createRoute() = "login"
    }

    object Home : Route("home/{username}") {
        const val USER_NAME = "username"

        val argumentsList = listOf(
            navArgument(USER_NAME) { type = NavType.StringType },
        )

        fun createRoute(userName: String) =
            "home/$userName"
    }
//    object Login : Route("login/{loginid}/{username}?lastname={lastname}") {
//        const val LOGIN_ID = "loginid"
//        const val USER_NAME = "username"
//        const val LAST_NAME = "lastname"
//
//        val argumentsList = listOf(
//            navArgument(LOGIN_ID) { type = NavType.IntType },
//            navArgument(USER_NAME) { type = NavType.StringType },
//            navArgument(LAST_NAME) { type = NavType.StringType }
//        )
//
//        fun createRoute(loginId: Int, userName: String, lastname: String?) =
//            "login/$loginId/$userName?lastname=$lastname"
//    }
}