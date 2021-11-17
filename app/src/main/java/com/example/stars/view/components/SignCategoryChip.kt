package com.example.stars.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SignCategoryChip(sign: String, dateRange: String, image: Int) {
    Surface(
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp,
        shape = CircleShape,
        color = MaterialTheme.colors.primary
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Icon(
                painter = painterResource(id = image),
                contentDescription = "Star Sign Logo",
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .padding(8.dp)
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(sign, modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(
                    dateRange,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}