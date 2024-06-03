package com.example.sakhcast.ui.main_screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sakhcast.ui.main_screens.home_screen.movie.MovieCategoryView
import com.example.sakhcast.ui.main_screens.home_screen.recently_watched.ContinueWatchView
import com.example.sakhcast.ui.main_screens.home_screen.series.SeriesCategoryView
import com.example.sakhcast.ui.theme.SakhCastTheme

@Preview
@Composable
fun HomeScreen(paddingValues: PaddingValues, homeScreenState: HomeScreenViewModel.HomeScreenState) {
    val seriesList = homeScreenState.seriesList
    val movieList = homeScreenState.moviesList
    val movie = homeScreenState.lastMovieWatched
    val series = homeScreenState.lastSeriesWatched
    val scrollState = rememberScrollState()

    SakhCastTheme() {
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            if (movie != null && series != null) {
                ContinueWatchView(movie, series)
            }
            SeriesCategoryView(seriesList)
            MovieCategoryView(movieCardList = movieList)
        }
    }
}

