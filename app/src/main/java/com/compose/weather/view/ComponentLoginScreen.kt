package com.compose.weather.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ComponentLoginScreen(
    navController: NavController,
    loginId: Int,
    userName: String,
    lastname: String?
) {
    Text(text = "login screen $loginId $userName $lastname")
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ComponentLoginScreen(rememberNavController(), 890, "user name", null)
}