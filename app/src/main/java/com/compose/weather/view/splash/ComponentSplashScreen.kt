package com.compose.weather.view.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.weather.R
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
            navigateToHome.invoke(Route.AuthNav.Dashboard.createRoute(SharedPrefsManager.userId))
        } else {
            navigateToLogin.invoke(Route.AuthNav.Login.createRoute())
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
            Text(text = stringResource(id = R.string.hows))
        }
        AnimatedVisibility(
            visible = visibleThe,
        ) {
            Button(onClick = {}) {
                Text(text = stringResource(id = R.string.the))
            }
        }
        AnimatedVisibility(
            visible = visibleWeather,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000))
        ) {
            Button(onClick = {}) {
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = stringResource(id = R.string.weather)
                )
            }
        }
        AnimatedVisibility(
            visible = visibleToday,
        ) {
            Button(onClick = {}) {
                Text(text = stringResource(id = R.string.today))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ComponentSplashScreen({}, {})
}