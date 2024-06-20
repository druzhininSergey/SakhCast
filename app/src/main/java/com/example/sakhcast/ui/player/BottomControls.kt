package com.example.sakhcast.ui.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.sakhcast.R
import com.example.sakhcast.data.formatMinSec

@Composable
fun BottomControls(
    modifier: Modifier = Modifier,
    totalDuration: () -> Long,
    currentTime: () -> Long,
    bufferedPercentage: () -> Int,
    onSeekChanged: (timeMs: Float) -> Unit,
    onFullscreenToggle: () -> Unit,
    onSettingsClick: () -> Unit
) {
    var isExpandedFavorite by remember { mutableStateOf(false) }

    val duration = remember(totalDuration()) { totalDuration() }

    val videoTime = remember(currentTime()) { currentTime() }

    val listFavoriteType = mapOf(
        "Буду смотреть" to "will",
        "Просмотренно" to "watched"
    )


    Column(modifier = modifier.padding(bottom = 32.dp)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = videoTime.toFloat(),
                onValueChange = onSeekChanged,
                valueRange = 0f..duration.toFloat(),
                colors = SliderDefaults.colors(
                    thumbColor = Color.Gray,
                    activeTickColor = Color.Gray
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = duration.formatMinSec(),
                color = Color.Gray
            )
            Row {
                IconButton(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onClick = onFullscreenToggle
                ) {
                    Image(
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.ic_fullscreen),
                        contentDescription = "Enter/Exit fullscreen"
                    )
                }
                Box {
                    IconButton(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        onClick =
//                            isExpandedFavorite = true
                            onSettingsClick

                    ) {
                        Image(
                            contentScale = ContentScale.Crop,
                            painter = painterResource(id = R.drawable.ic_settings),
                            contentDescription = "Enter/Exit fullscreen"
                        )
                    }
                    DropdownMenu(
                        modifier = Modifier.background(
                            color = Color.Gray.copy(alpha = 0.5f)
                        ),
                        offset = DpOffset(0.dp, 8.dp),
                        expanded = isExpandedFavorite,
                        onDismissRequest = { isExpandedFavorite = false },
                    ) {
                        listFavoriteType.keys.forEach { favType ->
                            DropdownMenuItem(text = { Text(text = favType) }, onClick = {})
                        }
                    }
                }
            }
        }
    }
}

