package com.compose.weather.viewmodel

import com.compose.weather.preferences.SharedPrefsManager
import com.compose.weather.view.login.LoginState
import com.compose.weather.view.login.UILogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : BaseViewModel() {

    val uiLogin = UILogin()

    val loginState = MutableStateFlow<LoginState>(LoginState.Default)

    fun login() = launchWithViewModelScope {
        loginState.update { LoginState.Loading }

        // Dummy fail scenario, display toast message in case of fail
        if (uiLogin.loginId.state.value.equals("fail", ignoreCase = true)) {
            loginState.update { LoginState.Failure("Value 'fail' is not accepted for login id") }
        } else {
            delay(2000)
            SharedPrefsManager.isUserLoggedIn = true
            SharedPrefsManager.userId = uiLogin.loginId.state.value
            loginState.update { LoginState.Success }
        }
    }

    fun combineAndValidate(): Flow<Boolean> {
        return combine(
            uiLogin.loginId.state,
            uiLogin.password.state
        ) { _, _ ->
            validate()
        }
    }

    private fun validate(): Boolean {
        uiLogin.validate()

        val hasError = uiLogin.hasError()
        uiLogin.enableLoginButton.update { hasError.not() }
        return hasError
    }
}