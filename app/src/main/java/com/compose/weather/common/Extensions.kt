package com.compose.weather.common

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun Bundle.getStringOrThrowException(key: String): String {
    val value = getString(key)
    requireNotNull(value) { "$key parameter wasn't found. Please make sure it's set!" }
    return value
}

fun String.Companion.empty() = ""

@Composable
fun <T> getMutableStateValue(state: MutableStateFlow<T>): T {
//    return state.collectAsState().value
    return state.collectAsStateWithLifecycle().value
}

@Composable
fun <T> getStateValue(state: StateFlow<T>): T {
//    return state.collectAsState().value
    return state.collectAsStateWithLifecycle().value
}

fun <T> setMutableStateValue(state: MutableStateFlow<T>, value: T) {
    state.value = value
}