package com.compose.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    fun launchWithViewModelScope(call: suspend () -> Unit, exceptionCallback: (exception: String) -> Unit) {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            exceptionCallback(exception.localizedMessage ?: "Something went wrong")
        }) {
            call.invoke()
        }
    }
}