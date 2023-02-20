package com.compose.weather.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.weather.R
import com.compose.weather.common.mutableStateValue
import com.compose.weather.common.setMutableStateValue
import com.compose.weather.navigtion.Route
import com.compose.weather.viewmodel.LoginScreenViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ComponentLoginScreen(
    navigateToHome: (route: String) -> Unit
) {
    BottomSheetLayout {
        Content(navigateToHome)
    }
}

@Composable
fun Content(navigateToHome: (route: String) -> Unit, vm: LoginScreenViewModel = viewModel()) {

    Log.d("Content", "Content")

    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpaceTop(20.dp)
        OutlinedTextField(
            label = { Text(stringResource(id = R.string.login_id)) },
            value = mutableStateValue(state = vm.loginId), onValueChange = {
                setMutableStateValue(state = vm.loginId, value = it)
            })
        SpaceTop(12.dp)
        OutlinedTextField(
            label = { Text(stringResource(id = R.string.password)) },
            value = mutableStateValue(state = vm.password), onValueChange = {
                setMutableStateValue(state = vm.password, value = it)
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        SpaceTop(12.dp)
        Button(onClick = {
//            vm.login()
            navigateToHome.invoke(Route.Home.createRoute("Test"))
        }) {
            Text(text = stringResource(id = R.string.login))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ComponentLoginScreen({})
}