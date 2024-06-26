package com.example.sakhcast.ui.category_screens

import android.os.Build
import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.sakhcast.R
import com.example.sakhcast.model.SeriesCard
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun PreviewSeriesCategoryCardItem() {
//    SakhCastApplicationTheme() {
//        SeriesCategoryCardItem(
//            seriesCard = SeriesCard(
//                id = 1,
//                name = "Сериал 1",
//                imdbRating = 9.0,
//                kinopoiskRating = 10.0,
//                releaseYear = 2022,
//                totalSeasonsAndSeries = "1 Сезон 23 Cерии"
//            ),
//            navHostController = navHostController
//        )
//    }
}

@Composable
fun SeriesCategoryCardItem(
    seriesCard: SeriesCard,
    navigateToSeriesById: (String) -> Unit
) {
    Box(modifier = Modifier.clickable { navigateToSeriesById(seriesCard.id.toString()) }) {
        Column() {
            SeriesCategoryCard(seriesCard)
            Text(
                text = seriesCard.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = seriesCard.year.toString(), fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = seriesCard.seasons, fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun SeriesCategoryCard(seriesCard: SeriesCard) {
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
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                if (seriesCard.imdb) {
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
                            Text(
                                modifier = Modifier.padding(start = 3.dp),
                                text = String.format(Locale.US, "%.1f", seriesCard.imdbRating),
                                color = Color.White,
                                fontSize = 8.sp
                            )
                        }
                    }
                }
                if (seriesCard.kp) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.Gray.copy(alpha = 0.7f),
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
                            Text(
                                modifier = Modifier.padding(start = 3.dp),
                                text = String.format(Locale.US, "%.1f", seriesCard.kpRating),
                                color = Color.White,
                                fontSize = 8.sp
                            )
                        }
                    }
                }
            }
        }
    }
}