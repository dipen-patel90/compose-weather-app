package com.compose.weather.view.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SpaceTop(top: Dp) {
    Spacer(modifier = Modifier.padding(PaddingValues(top = top)))
}