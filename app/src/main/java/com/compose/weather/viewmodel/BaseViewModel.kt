package com.compose.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
//        Timber.e(exception, "exceptionHandler *== " + exception.cause)
//        stopLoading()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
//                if (ConnectivityUtils.isConnectedToInternet(applicationContext)) {
//                    setError(genericErrorResponse())
//                } else {
//                    setError(noInternetErrorResponse())
//                }
            }
        }
    }


    fun launchWithViewModelScope(call: suspend () -> Unit) {
        viewModelScope.launch(exceptionHandler) {
            call.invoke()
        }
    }
}