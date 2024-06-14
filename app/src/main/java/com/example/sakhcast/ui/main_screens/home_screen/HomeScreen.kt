package com.example.sakhcast.ui.main_screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sakhcast.Colors
import com.example.sakhcast.ui.main_screens.home_screen.movie.MovieCategoryView
import com.example.sakhcast.ui.main_screens.home_screen.recently_watched.ContinueWatchView
import com.example.sakhcast.ui.main_screens.home_screen.series.SeriesCategoryView
import com.example.sakhcast.ui.theme.SakhCastTheme

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {
    val homeScreenState by
    homeScreenViewModel.homeScreenState.observeAsState(HomeScreenViewModel.HomeScreenState())

    val seriesList = homeScreenState.seriesList
    val movieList = homeScreenState.moviesList
    val lastWatchedMovieTime = homeScreenState.lastWatchedMovieTime
    val movie = homeScreenState.lastWatched?.movie
    val series = homeScreenState.lastWatched?.serial
    val scrollState = rememberScrollState()

    SakhCastTheme {
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            if (movie != null && series != null && seriesList != null && movieList != null) {
                ContinueWatchView(movie, series, lastWatchedMovieTime, navHostController)
                SeriesCategoryView(seriesList, navHostController)
                MovieCategoryView(movieList, navHostController)
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        trackColor = Colors.blueColor
                    )
                }
            }
        }
    }
}
