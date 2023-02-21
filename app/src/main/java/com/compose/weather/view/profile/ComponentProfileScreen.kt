package com.compose.weather.view.profile

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ComponentProfileScreen() {
    Text(text = "Profile Screen")
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ComponentProfileScreen()
}