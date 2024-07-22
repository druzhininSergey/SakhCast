package com.example.sakhcast.ui.main_screens.home_screen.series

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.sakhcast.R
import com.example.sakhcast.data.samples.SeriesCardSample
import com.example.sakhcast.model.SeriesCard
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun PreviewSeriesItemView() {
    SeriesItemView(
        seriesCard = SeriesCardSample.seriesCard
    ) {}
}

@Composable
fun SeriesItemView(
    seriesCard: SeriesCard,
    isFavoriteScreen: Boolean = false,
    navigateToSeriesById: (String) -> Unit
) {
    val viewedSeries = seriesCard.progress?.viewed ?: 0
    val amountSeries = seriesCard.progress?.amount ?: 100
    val progress = viewedSeries / amountSeries.toFloat()

    Box(
        modifier = Modifier.clickable { navigateToSeriesById(seriesCard.id.toString()) }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            SeriesCard(seriesCard, isFavoriteScreen)
            if (isFavoriteScreen) {
                LinearProgressIndicator(
                    color = if (progress == 1.0F) Color.Green else Color.Yellow,
                    trackColor = Color.Black,
                    progress = { progress },
                    modifier = Modifier
                        .padding(top = 3.dp)
                        .width(140.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(10.dp)),
                )
            }
            Text(
                text = seriesCard.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.width(150.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.width(150.dp)
            ) {
                Text(
                    text = seriesCard.year.toString(), fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = seriesCard.seasons, fontSize = 8.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun SeriesCard(seriesCard: SeriesCard, isFavoriteScreen: Boolean) {
    val imageUrl = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) seriesCard.coverAlt + ".avif"
    else seriesCard.coverAlt + ".webp"

    val backdropColor1 =
        Color(android.graphics.Color.parseColor(seriesCard.coverColors?.background1 ?: "#000000"))
    val backdropColor2 =
        Color(android.graphics.Color.parseColor(seriesCard.coverColors?.background2 ?: "#000000"))
    val brush = Brush.verticalGradient(listOf(backdropColor1, backdropColor2))

    Card(
        modifier = Modifier
            .width(150.dp)
            .height(220.dp),
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
}

@Composable
fun NotFavRatingSeriesCard(isImdb: Boolean, isKp: Boolean, imdbRating: Double?, kpRating: Double?) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,

        ) {
        if (isImdb) {
            Box(
                modifier = Modifier
                    .background(
                        color = Color.Gray.copy(alpha = 0.8f),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_imdb),
                        contentDescription = null
                    )
                    val formattedRatingImdb =
                        String.format(Locale.US, "%.1f", imdbRating)
                    Text(
                        modifier = Modifier.padding(start = 3.dp),
                        text = formattedRatingImdb,
                        color = Color.White,
                        fontSize = 8.sp
                    )
                }
            }
        }
        if (isKp) {
            Box(
                modifier = Modifier
                    .background(
                        color = Color.Gray.copy(alpha = 0.8f),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_kinopoisk),
                        contentDescription = null,
                    )
                    val formattedRatingKp =
                        String.format(Locale.US, "%.1f", kpRating)
                    Text(
                        modifier = Modifier.padding(start = 3.dp),
                        text = formattedRatingKp,
                        color = Color.White,
                        fontSize = 8.sp
                    )
                }
            }
        }
    }
}

@Composable
fun FavRatingSeriesCard(
    isImdb: Boolean,
    isKp: Boolean,
    imdbRating: Double?,
    kpRating: Double?,
    newEpisodes: Int
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,

        ) {
        Row(
            modifier = Modifier
                .background(
                    color = Color.Gray.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            if (isImdb) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(vertical = 0.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_imdb),
                            contentDescription = null
                        )
                        val formattedRatingImdb =
                            String.format(Locale.US, "%.1f", imdbRating)
                        Text(
                            modifier = Modifier.padding(start = 3.dp),
                            text = formattedRatingImdb,
                            color = Color.White,
                            fontSize = 8.sp
                        )
                    }
                }
            }
            if (isKp) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(vertical = 0.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_kinopoisk),
                            contentDescription = null,
                        )
                        val formattedRatingKp =
                            String.format(Locale.US, "%.1f", kpRating)
                        Text(
                            modifier = Modifier.padding(start = 3.dp),
                            text = formattedRatingKp,
                            color = Color.White,
                            fontSize = 8.sp
                        )
                    }
                }
            }
        }
        if (newEpisodes != 0) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        color = Color.Red.copy(alpha = 0.8f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = newEpisodes.toString(),
                    modifier = Modifier,
                    color = Color.White,
                    fontSize = 10.sp
                )
            }
        }
    }
}


