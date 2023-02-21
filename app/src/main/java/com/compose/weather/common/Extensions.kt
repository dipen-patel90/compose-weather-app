package com.compose.weather.common

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.MutableStateFlow

fun Bundle.getStringOrThrowException(key: String): String {
    val value = getString(key)
    requireNotNull(value) { "$key parameter wasn't found. Please make sure it's set!" }
    return value
}

fun String.Companion.empty() = ""

@Composable
fun <T> getMutableStateValue(state: MutableStateFlow<T>): T {
    return state.collectAsState().value
}

fun <T> setMutableStateValue(state: MutableStateFlow<T>, value: T) {
    state.value = value
}