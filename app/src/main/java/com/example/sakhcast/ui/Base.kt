package com.example.sakhcast.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DividerBase(){
    HorizontalDivider(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 16.dp),
        thickness = 1.dp,
        color = Color.Gray
    )
}