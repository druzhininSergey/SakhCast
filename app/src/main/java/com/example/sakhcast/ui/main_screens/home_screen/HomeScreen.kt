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
import com.example.sakhcast.data.Samples
import com.example.sakhcast.ui.main_screens.home_screen.movie.MovieCategoryView
import com.example.sakhcast.ui.main_screens.home_screen.recently_watched.ContinueWatchView
import com.example.sakhcast.ui.main_screens.home_screen.series.SeriesCategoryView
import com.example.sakhcast.ui.theme.SakhCastTheme

@Preview
@Composable
fun HomeScreen(paddingValues: PaddingValues) {
    val seriesList = Samples.getAllSeries()
    val movieList = Samples.getAllMovies()
    val scrollState = rememberScrollState()

    SakhCastTheme() {
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            ContinueWatchView()
            SeriesCategoryView(seriesList)
            MovieCategoryView(movieCardList = movieList)
        }
    }
}

