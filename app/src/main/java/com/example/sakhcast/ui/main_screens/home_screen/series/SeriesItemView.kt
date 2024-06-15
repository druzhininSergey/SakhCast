package com.example.sakhcast.ui.main_screens.home_screen.series

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.example.sakhcast.R
import com.example.sakhcast.SERIES_VIEW
import com.example.sakhcast.model.SeriesCard
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun PreviewSeriesItemView() {
//    SakhCastTheme() {
//        SeriesItemView(
//            seriesCard = SeriesCard(
//                id = 1,
//                name = "Сериал 1",
//                imdbRating = 9.0,
//                kinopoiskRating = 10.0,
//                releaseYear = 2022,
//                totalSeasonsAndSeries = "1 Сезон 23 Cерии"
//            )
//        )
//    }
}

@Composable
fun SeriesItemView(seriesCard: SeriesCard, navHostController: NavHostController) {
    Box(
        modifier = Modifier.clickable { navHostController.navigate("${SERIES_VIEW}/${seriesCard.id}") }
    ) {
        Column() {
            SeriesCard(seriesCard)
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
                Text(
                    text = seriesCard.seasons, fontSize = 8.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun SeriesCard(seriesCard: SeriesCard) {
    val imageUrl = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) seriesCard.coverAlt + ".avif"
    else seriesCard.coverAlt + ".webp"

    val backdropColor1 =
        Color(android.graphics.Color.parseColor(seriesCard.coverColors.background1))
    val backdropColor2 =
        Color(android.graphics.Color.parseColor(seriesCard.coverColors.background2))
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
                            val formattedRatingImdb =
                                String.format(Locale.US, "%.1f", seriesCard.imdbRating)
                            Text(
                                modifier = Modifier.padding(start = 3.dp),
                                text = formattedRatingImdb,
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
                                String.format(Locale.US, "%.1f", seriesCard.kpRating)
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
    }
}

