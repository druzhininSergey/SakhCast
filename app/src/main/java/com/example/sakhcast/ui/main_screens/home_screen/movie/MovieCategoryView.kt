package com.example.sakhcast.ui.main_screens.home_screen.movie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sakhcast.Dimens
import com.example.sakhcast.model.MovieList

@Composable
fun MovieCategoryView(
    movieList: MovieList,
    navigateToMovieByAlphaId: (String) -> Unit,
    navigateToCatalogAllMovies: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = Dimens.mainPadding, start = Dimens.mainPadding)
            .clickable { navigateToCatalogAllMovies() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Фильмы",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Icon(
            modifier = Modifier.padding(top = 4.dp, start = 5.dp),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null
        )
    }
    LazyRow(
        modifier = Modifier.padding(top = Dimens.mainPadding),
        contentPadding = PaddingValues(start = Dimens.mainPadding, end = Dimens.mainPadding),
        horizontalArrangement = Arrangement.spacedBy(Dimens.mainPadding)
    ) {
        items(
            items = movieList.items,
            key = { it.id }
        ) { item ->
            MovieItemView(movieCard = item, navigateToMovieByAlphaId)
        }
    }
}
