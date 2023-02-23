package com.compose.weather.view.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.weather.R
import com.compose.weather.view.common.BottomSheetLayout
import com.compose.weather.view.common.SpaceTop

@Composable
fun ComponentSettingsScreen(
    logout: () -> Unit
) {
    // rememberSaveable will keep the state store across the orientation
    var openLogoutConfirmation by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            onClick = {
                openLogoutConfirmation = true
            }) {
            Text(text = stringResource(id = R.string.logout))
        }
    }

    if (openLogoutConfirmation) {
        BottomSheetLayout {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Are you sure you want to logout?")
                SpaceTop(top = 20.dp)
                Button(onClick = {
                    logout.invoke()
                }) {
                    Text(text = "Yes")
                }
                SpaceTop(top = 20.dp)
                Button(onClick = {
                    openLogoutConfirmation = false
                }) {
                    Text(text = "Cancel")
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ComponentSettingsScreen({})
}