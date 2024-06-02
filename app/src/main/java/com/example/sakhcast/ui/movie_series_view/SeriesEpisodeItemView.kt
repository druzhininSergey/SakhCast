package com.example.sakhcast.ui.movie_series_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sakhcast.R
import com.example.sakhcast.data.SeriesEpisodesSample
import com.example.sakhcast.model.Episode

@Preview(showBackground = true)
@Composable
fun PreviewSeriesEpisodeView() {
    val episodes = SeriesEpisodesSample.getSeriesEpisodes()
    SeriesEpisodeView(episodes)
}

@Preview(showBackground = true)
@Composable
fun PreviewSeriesEpisodeItemView() {
    val episodes = SeriesEpisodesSample.getSeriesEpisodes()[0]
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
    Card(modifier = Modifier.width(190.dp)) {
        Box() {
            Image(
                modifier = Modifier.height(123.dp),
                painter = painterResource(id = R.drawable.series_episode_preview),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(20.dp)
                    .background(
                        color = Color.Gray.copy(alpha = 0.3f),
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    fontSize = 9.sp,
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