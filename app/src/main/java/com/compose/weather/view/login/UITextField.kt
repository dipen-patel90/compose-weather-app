package com.compose.weather.view.login

import com.compose.weather.R
import com.compose.weather.common.empty
import kotlinx.coroutines.flow.MutableStateFlow

data class UITextField(
    val state: MutableStateFlow<String> = MutableStateFlow(String.empty()),
    var hasError: Boolean = false,
    var showError: Boolean = false,
    var errorMessage: Int = R.string.empty,
)