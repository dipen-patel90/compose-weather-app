package com.compose.weather.viewmodel

import com.compose.weather.common.empty
import com.compose.weather.preferences.SharedPrefsManager
import com.compose.weather.view.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : BaseViewModel() {

    val loginId = MutableStateFlow(String.empty())
    val password = MutableStateFlow(String.empty())
    val loginState = MutableStateFlow<LoginState>(LoginState.Default)

    fun login() = launchWithViewModelScope {
        //        loginState.emit(LoginState.Loading)
        loginState.update { LoginState.Loading }

        if (loginId.value.isNotEmpty() && password.value.isNotEmpty()) {
            delay(2000)
            //            loginState.emit(LoginState.Success)
            loginState.update { LoginState.Success }
            SharedPrefsManager.isUserLoggedIn = true
            SharedPrefsManager.userId = loginId.value
        } else {
            //            loginState.emit(LoginState.Failure("Error: Empty value"))
            loginState.update { LoginState.Failure("Error: Empty value") }
        }
    }
}