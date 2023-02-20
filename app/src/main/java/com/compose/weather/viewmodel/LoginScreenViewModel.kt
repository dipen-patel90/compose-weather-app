package com.compose.weather.viewmodel

import com.compose.weather.common.empty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : BaseViewModel() {

    val loginId = MutableStateFlow(String.empty())
    val password = MutableStateFlow(String.empty())
    val loginState = MutableStateFlow(false)

    fun login() = launchWithViewModelScope {
        if (loginId.value.isNotEmpty() && password.value.isNotEmpty()) {
            loginState.emit(true)
        }
    }
}