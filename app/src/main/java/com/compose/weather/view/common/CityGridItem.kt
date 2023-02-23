package com.compose.weather.view.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.weather.model.ItemCity

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CityGridItem(city: ItemCity, onClick: (city: ItemCity) -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                onClick(city)
            },
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = city.icon),
                contentDescription = city.city,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(if (city.isSelected) Color.Blue else Color.Cyan)
            )
            Text(text = city.city)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CityGridItem(
        ItemCity("London")
    ) {}
}