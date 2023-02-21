package com.compose.weather.view.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.compose.weather.R

@Composable
fun ComponentSettingsScreen(
    logout: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Settings Screen")
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                logout.invoke()
            }) {
            Text(text = stringResource(id = R.string.logout))
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ComponentSettingsScreen({})
}