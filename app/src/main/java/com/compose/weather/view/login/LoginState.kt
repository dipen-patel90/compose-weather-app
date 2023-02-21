package com.compose.weather.view.login

sealed class LoginState {
    object Default : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    class Failure(val errorMessage: String) : LoginState()
}
