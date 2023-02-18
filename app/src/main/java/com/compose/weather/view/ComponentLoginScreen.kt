package com.compose.weather.view

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.compose.weather.navigtion.Route

@Composable
fun ComponentLoginScreen(
    navigateToHome: (route: String) -> Unit
) {
    Text(text = "login screen")
    Button(onClick = { navigateToHome.invoke(Route.Home.createRoute("Dipen")) }) {

    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ComponentLoginScreen({})
}