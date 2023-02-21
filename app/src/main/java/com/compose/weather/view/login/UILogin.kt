package com.compose.weather.view.login

import com.compose.weather.R
import kotlinx.coroutines.flow.MutableStateFlow

data class UILogin(
    val loginId: UITextField = UITextField(),
    val password: UITextField = UITextField(),
    var enableLoginButton: MutableStateFlow<Boolean> = MutableStateFlow(false)
) {
    fun validate() {

        loginId.apply {
            val login = state.value
            val loginLength = login.length

            hasError = login.isEmpty() || loginLength < 4
            showError = login.isNotEmpty() && hasError
            errorMessage = if (loginLength < 4) {
                R.string.login_id_length_error
            } else R.string.empty
        }

        password.apply {
            val pwd = state.value
            val pwdLength = pwd.length

            hasError = pwd.isEmpty() || pwdLength < 8
            showError = pwd.isNotEmpty() && hasError
            errorMessage = if (pwdLength < 8) {
                R.string.password_length_error
            } else R.string.empty
        }
    }

    fun hasError(): Boolean {
        return loginId.hasError || password.hasError
    }
}