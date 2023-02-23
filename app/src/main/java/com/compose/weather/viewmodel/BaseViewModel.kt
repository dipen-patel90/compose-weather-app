package com.compose.weather.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    fun launchWithViewModelScope(call: suspend () -> Unit) {
        launchWithViewModelScope(call) {
            Log.d("Coroutine", "Coroutine Exception $it")
        }
    }

    fun launchWithViewModelScope(
        call: suspend () -> Unit,
        exceptionCallback: (exception: String) -> Unit
    ) {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            exceptionCallback(exception.localizedMessage ?: "Something went wrong")
        }) {
            call.invoke()
        }
    }
}