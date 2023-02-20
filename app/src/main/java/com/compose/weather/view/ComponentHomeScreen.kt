package com.compose.weather.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ComponentHomeScreen(username:String
) {
    Text(text = "home screen $username")
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ComponentHomeScreen("")
}