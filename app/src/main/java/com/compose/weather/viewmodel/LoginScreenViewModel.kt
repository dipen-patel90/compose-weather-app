package com.compose.weather.viewmodel

import com.compose.weather.preferences.SharedPrefsManager
import com.compose.weather.repository.WeatherRepository
import com.compose.weather.view.login.LoginState
import com.compose.weather.view.login.UILogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    BaseViewModel() {

    val uiLogin = UILogin()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Default)
    val loginState = _loginState.asStateFlow()

    fun login() = launchWithViewModelScope(
        call = {
            _loginState.update { LoginState.Loading }

            // Dummy delay, Instead of this we can implement actual login API
            delay(3000)
            SharedPrefsManager.isUserLoggedIn = true
            SharedPrefsManager.userId = uiLogin.username.state.value
            _loginState.update { LoginState.Success }

//            weatherRepository.getWeatherDetail(33.44F, -94.04F).let { response ->
//                if (response.isSuccessful) {
//                    SharedPrefsManager.isUserLoggedIn = true
//                    SharedPrefsManager.userId = uiLogin.username.state.value
//                    loginState.update { LoginState.Success }
//                } else {
//                    loginState.update { LoginState.Failure(response.message()) }
//                }
//            }
        },
        exceptionCallback = { message ->
            _loginState.update { LoginState.Failure(message) }
        }
    )

    fun combineAndValidate(): Flow<Boolean> {
        return combine(
            uiLogin.username.state,
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