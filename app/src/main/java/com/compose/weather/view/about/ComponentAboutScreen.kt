package com.compose.weather.view.about

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ComponentAboutScreen(
) {
    Text(text = "About Screen")
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ComponentAboutScreen()
}