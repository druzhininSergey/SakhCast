package com.example.sakhcast.ui.movie_series_view

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.sakhcast.data.samples.SeriesEpisodesSample
import com.example.sakhcast.model.Episode

@Preview(showBackground = true)
@Composable
fun PreviewSeriesEpisodeView() {
    val episodes = SeriesEpisodesSample.getSeriesEpisodesList()
    SeriesEpisodeView(episodes)
}

@Preview(showBackground = true)
@Composable
fun PreviewSeriesEpisodeItemView() {
    val episodes = SeriesEpisodesSample.getSeriesEpisodesList()[0]
    SeriesEpisodeItemView(seriesEpisode = episodes)
}

@Composable
fun SeriesEpisodeView(episodes: List<Episode>) {
    LazyRow(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        itemsIndexed(episodes) { _, episode ->
            SeriesEpisodeItemView(seriesEpisode = episode)
        }
    }

}

@Composable
fun SeriesEpisodeItemView(seriesEpisode: Episode) {

    val imageUrl = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        seriesEpisode.previewAlt + ".avif"
    } else {
        seriesEpisode.previewAlt + ".webp"
    }

    val backdropColor1 = listOf(
        Color(0xFF616161),
        Color(0xFF78909C),
        Color(0xFF5D4037),
        Color(0xFF546E7A),
        Color(0xFF0D47A1),
        Color(0xFF1B5E20),
        Color(0xFF212121)
    ).random()
    val backdropColor2 = listOf(
        Color(0xFF757575),
        Color(0xFF90A4AE),
        Color(0xFF4E342E),
        Color(0xFF455A64),
        Color(0xFF1565C0),
        Color(0xFF2E7D32),
        Color(0xFF424242)
    ).random()
    val brush = Brush.verticalGradient(listOf(backdropColor1, backdropColor2))

    Card(modifier = Modifier.width(190.dp)) {
        Box() {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.height(123.dp),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(brush = brush)
                    )
                }
            )
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(30.dp)
                    .background(
                        color = Color.Gray.copy(alpha = 0.3f),
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    fontSize = 12.sp,
                    text = seriesEpisode.index,
                    color = Color.White,
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(text = seriesEpisode.name, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = seriesEpisode.date)
        }
    }
}