package com.compose.weather.view.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.weather.R
import com.compose.weather.common.getMutableStateValue
import com.compose.weather.navigtion.Route
import com.compose.weather.view.common.BottomSheetLayout
import com.compose.weather.view.common.Loader
import com.compose.weather.view.common.OutlinedTextFieldWithError
import com.compose.weather.view.common.SpaceTop
import com.compose.weather.viewmodel.LoginScreenViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ComponentLoginScreen(
    navigateToDashboard: (route: String) -> Unit,
    vm: LoginScreenViewModel = viewModel()
) {
    val loginState by vm.loginState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LaunchedEffect(key1 = loginState, block = {
        if (loginState == LoginState.Success) {
            Log.d("Login", "Login SUCCESS ## Moving to Home screen")
            navigateToDashboard.invoke(Route.Dashboard.createRoute(vm.uiLogin.loginId.state.value))
        } else if (loginState is LoginState.Failure) {
            val message = (loginState as LoginState.Failure).errorMessage
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    })

    LaunchedEffect(key1 = true, block = {
        Log.d("Login", "One time call")
        vm.combineAndValidate().collectLatest {
            if (it) {
                Log.d("Validate", "Form has error: $it")
            }
        }
    })

    when (loginState) {
        LoginState.Default -> {
            LoginBottomSheet(vm)
        }
        is LoginState.Failure -> {
            LoginBottomSheet(vm)
        }
        LoginState.Loading -> {
            Loader()
        }
        LoginState.Success -> {
            Log.d("Login", "Login SUCCESS")
        }
    }
}

@Composable
private fun LoginBottomSheet(vm: LoginScreenViewModel) {
    BottomSheetLayout {
        Content(vm)
    }
}

@Composable
private fun Content(vm: LoginScreenViewModel) {

    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpaceTop(20.dp)
        OutlinedTextFieldWithError(vm.uiLogin.loginId, R.string.login_id)
        SpaceTop(12.dp)
        OutlinedTextFieldWithError(vm.uiLogin.password, R.string.password, true)

        SpaceTop(12.dp)
        Button(
            onClick = {
                vm.login()
            },
            enabled = getMutableStateValue(state = vm.uiLogin.enableLoginButton)
        ) {
            Text(text = stringResource(id = R.string.login))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ComponentLoginScreen({})
}