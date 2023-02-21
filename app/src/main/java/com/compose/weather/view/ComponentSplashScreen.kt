package com.compose.weather.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.compose.weather.navigtion.Route
import com.compose.weather.preferences.SharedPrefsManager
import kotlinx.coroutines.delay

@Composable
fun ComponentSplashScreen(
    navigateToLogin: (route: String) -> Unit,
    navigateToHome: (route: String) -> Unit
) {

    var visibleThe by remember { mutableStateOf(false) }
    var visibleWeather by remember { mutableStateOf(false) }
    var visibleToday by remember { mutableStateOf(false) }

    // If we use "rememberSaveable" instead of "remember",
    // state will be saved across the orientation
//    var visibleThe by rememberSaveable { mutableStateOf(false) }
//    var visibleWeather by rememberSaveable { mutableStateOf(false) }
//    var visibleToday by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        delay(300)
        visibleThe = true
        delay(500)
        visibleWeather = true
        delay(500)
        visibleToday = true
        delay(500)
        if (SharedPrefsManager.isUserLoggedIn) {
            navigateToHome.invoke(Route.Home.createRoute(SharedPrefsManager.userId))
        } else {
            navigateToLogin.invoke(Route.Login.createRoute())
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {}) {
            Text(text = "How's")
        }
        AnimatedVisibility(
            visible = visibleThe,
        ) {
            Button(onClick = {}) {
                Text(text = "the")
            }
        }
        AnimatedVisibility(
            visible = visibleWeather,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000))
        ) {
            Button(onClick = {}) {
                Text(text = "        Weather        ")
            }
        }
        AnimatedVisibility(
            visible = visibleToday,
        ) {
            Button(onClick = {}) {
                Text(text = "Today")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ComponentSplashScreen({}, {})
}