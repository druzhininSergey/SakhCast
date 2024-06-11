package com.example.sakhcast.ui.main_screens.favorites_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.navigation.NavHostController
import com.example.sakhcast.Dimens
import com.example.sakhcast.model.MovieList
import com.example.sakhcast.ui.main_screens.home_screen.movie.MovieItemView

@Composable
fun MoviesPage(movieCardsWillWatch: MovieList?, navHostController: NavHostController) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(
                    horizontal = Dimens.mainPadding,
                    vertical = Dimens.mainPadding
                ),
                text = "Буду смотреть",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Icon(
                modifier = Modifier.padding(top = 4.dp),
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null
            )
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = Dimens.mainPadding),
            horizontalArrangement = Arrangement.spacedBy(Dimens.mainPadding)
        ) {
            if (movieCardsWillWatch != null) {
                itemsIndexed(movieCardsWillWatch.items) { _, movie ->
                    MovieItemView(movieCard = movie, navHostController = navHostController)
                }
            }
        }
    }
}