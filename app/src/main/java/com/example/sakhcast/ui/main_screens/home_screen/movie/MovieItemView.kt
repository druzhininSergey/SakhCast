package com.example.sakhcast.ui.main_screens.home_screen.movie

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.sakhcast.MOVIE_VIEW
import com.example.sakhcast.R
import com.example.sakhcast.model.MovieCard
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun PreviewMovieItemView() {
//    SakhCastTheme(darkTheme = true) {
//        MovieItemView(
//            movieCard = MovieCard(
//                id = 1,
//                ruTitle = "Фильм 1",
//                imdbRating = 9.0,
//                kinopoiskRating = 10.0,
//                releaseDate = "2022",
//                duration = "1ч 30 мин"
//            )
//        )
//    }
}

@Composable
fun MovieItemView(movieCard: MovieCard, navHostController: NavHostController) {
    Box(
        Modifier.clickable { navHostController.navigate("${MOVIE_VIEW}/${movieCard.idAlpha}") }
    ) {
        Column() {

            MovieCard(movieCard)
            Text(
                text = movieCard.ruTitle,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.width(150.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .width(150.dp)
            ) {
                Text(
                    text = movieCard.releaseDate.take(4),
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                if (movieCard.runtime != null) Text(
                    text = convertMinutes(movieCard.runtime),
                    fontSize = 8.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

private fun convertMinutes(minutes: Int): String {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60
    val hoursPart = if (hours > 0) "${hours}час. " else ""
    val minutesPart = if (remainingMinutes > 0 || hours == 0) "${remainingMinutes}мин." else ""
    return "$hoursPart$minutesPart".trim()
}

@Composable
fun MovieCard(movieCard: MovieCard) {
    val context = LocalContext.current
    val imageUrl = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        movieCard.coverAlt + ".avif"
    } else {
        movieCard.coverAlt + ".webp"
    }
    val coverPainter: Painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = imageUrl)
                .apply(block = fun ImageRequest.Builder.() {
                    crossfade(true)
                    placeholder(R.drawable.series_poster) // Укажите ресурс-заполнитель
                    //            error(R.drawable.error) // Укажите ресурс ошибки
                }).build()
        )
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(220.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(),
    ) {
        Box {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = coverPainter,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                if (movieCard.imdb)
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
                                String.format(Locale.US, "%.1f", movieCard.imdbRating)
                            Text(
                                modifier = Modifier.padding(start = 3.dp),
                                text = formattedRatingImdb,
                                color = Color.White,
                                fontSize = 8.sp
                            )
                        }
                    }
                if (movieCard.kp)
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
                                String.format(Locale.US, "%.1f", movieCard.kpRating)
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