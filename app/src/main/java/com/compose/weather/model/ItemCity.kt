package com.compose.weather.model

import androidx.annotation.DrawableRes
import com.compose.weather.R

data class ItemCity(
    val city: String,
    @DrawableRes val icon: Int = R.drawable.ic_launcher_foreground,
    var isSelected: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}