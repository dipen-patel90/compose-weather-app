package com.compose.weather.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.compose.weather.navigtion.Route

@Composable
fun ComponentSplashScreen(navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "splash screen")
        Button(onClick = {
            navController.navigate(Route.Login.createRoute(123, "dipen.ptl1", "abc"))
        }) {
            Text(text = "Click To Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ComponentSplashScreen(rememberNavController())
}