package com.example.sakhcast.ui.main_screens.home_screen.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sakhcast.R
import com.example.sakhcast.model.MovieCard
import com.example.sakhcast.ui.theme.SakhCastTheme

@Preview(showBackground = true)
@Composable
fun PreviewMovieItemView() {
    SakhCastTheme(darkTheme = true) {
        MovieItemView(
            movieCard = MovieCard(
                id = 1,
                ruTitle = "Фильм 1",
                imdbRating = 9.0,
                kinopoiskRating = 10.0,
                releaseDate = "2022",
                duration = "1ч 30 мин"
            )
        )
    }
}

@Composable
fun MovieItemView(movieCard: MovieCard) {
    Box {
        Column() {
            MovieCard(movieCard)
            Text(
                text = movieCard.ruTitle,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .width(150.dp)
            ) {
                Text(
                    text = movieCard.releaseDate.toString(),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = movieCard.duration,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun MovieCard(movieCard: MovieCard) {
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
                painter = painterResource(id = R.drawable.movie_poster),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
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
                            text = movieCard.imdbRating.toString(),
                            color = Color.White,
                            fontSize = 8.sp
                        )
                    }
                }
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
                            text = movieCard.kinopoiskRating.toString(),
                            color = Color.White,
                            fontSize = 8.sp
                        )
                    }
                }
            }
        }
    }
}