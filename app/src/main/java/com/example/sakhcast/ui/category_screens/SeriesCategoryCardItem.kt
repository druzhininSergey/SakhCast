package com.example.sakhcast.ui.category_screens

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.sakhcast.data.samples.SeriesCardSample
import com.example.sakhcast.model.SeriesCard
import com.example.sakhcast.ui.main_screens.home_screen.series.FavRatingSeriesCard
import com.example.sakhcast.ui.main_screens.home_screen.series.NotFavRatingSeriesCard

@Preview(showBackground = true)
@Composable
fun PreviewSeriesCategoryCardItem() {
    SeriesCategoryCardItem(
        seriesCard = SeriesCardSample.seriesCard
    ) {}
}

@Composable
fun SeriesCategoryCardItem(
    seriesCard: SeriesCard,
    isFavoriteScreen: Boolean = false,
    navigateToSeriesById: (String) -> Unit
) {
    Box(modifier = Modifier.clickable { navigateToSeriesById(seriesCard.id.toString()) }) {
        Column {
            SeriesCategoryCard(seriesCard, isFavoriteScreen)
            Text(
                text = seriesCard.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(3.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = seriesCard.year.toString(), fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = seriesCard.seasons, fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun SeriesCategoryCard(seriesCard: SeriesCard, isFavoriteScreen: Boolean) {
    val imageUrl = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        seriesCard.coverAlt + ".avif"
    } else {
        seriesCard.coverAlt + ".webp"
    }
    val backdropColor1 =
        Color(android.graphics.Color.parseColor(seriesCard.coverColors?.background1 ?: "#000000"))
    val backdropColor2 =
        Color(android.graphics.Color.parseColor(seriesCard.coverColors?.background2 ?: "#000000"))
    val brush = Brush.verticalGradient(listOf(backdropColor1, backdropColor2))
    val viewedSeries = seriesCard.progress?.viewed ?: 0
    val amountSeries = seriesCard.progress?.amount ?: 100
    val progress = viewedSeries / amountSeries.toFloat()

    Column {
        Card(
            modifier = Modifier
                .aspectRatio(0.682f),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(),
        ) {
            Box {
                SubcomposeAsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                    loading = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(brush = brush)
                        )
                    }
                )
                if (!isFavoriteScreen)
                    NotFavRatingSeriesCard(
                        isImdb = seriesCard.imdb,
                        isKp = seriesCard.kp,
                        imdbRating = seriesCard.imdbRating,
                        kpRating = seriesCard.kpRating
                    )
                else FavRatingSeriesCard(
                    isImdb = seriesCard.imdb,
                    isKp = seriesCard.kp,
                    imdbRating = seriesCard.imdbRating,
                    kpRating = seriesCard.kpRating,
                    newEpisodes = seriesCard.newEpisodes
                )
            }
        }
        if (isFavoriteScreen) {
            LinearProgressIndicator(
                color = if (progress == 1.0F) Color.Green else Color.Yellow,
                trackColor = Color.Black,
                progress = { progress },
                modifier = Modifier
                    .padding(top = 3.dp)
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(10.dp)),
            )
        }
    }
}
