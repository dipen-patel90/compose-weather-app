package com.compose.weather.common

import android.os.Bundle

fun Bundle.getStringOrThrowException(key: String): String {
    val value = getString(key)
    requireNotNull(value) { "$key parameter wasn't found. Please make sure it's set!" }
    return value
}