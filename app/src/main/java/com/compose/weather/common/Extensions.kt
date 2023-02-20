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
fun mutableStateValue(state: MutableStateFlow<String>): String {
    return state.collectAsState().value
}

fun setMutableStateValue(state: MutableStateFlow<String>, value: String) {
    state.value = value
}