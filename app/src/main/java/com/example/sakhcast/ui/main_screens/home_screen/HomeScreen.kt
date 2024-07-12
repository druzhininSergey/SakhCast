package com.example.sakhcast.ui.main_screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sakhcast.Colors
import com.example.sakhcast.ui.main_screens.home_screen.movie.MovieCategoryView
import com.example.sakhcast.ui.main_screens.home_screen.recently_watched.ContinueWatchView
import com.example.sakhcast.ui.main_screens.home_screen.series.SeriesCategoryView
import com.example.sakhcast.ui.theme.SakhCastTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    navigateToMovieByAlphaId: (String) -> Unit,
    navigateToSeriesById: (String) -> Unit,
    navigateToCatalogAllSeries: () -> Unit,
    navigateToCatalogAllMovies: () -> Unit,
    allScreensHomeState: StateFlow<HomeScreenViewModel.HomeScreenState>,
    loadDataToHomeScreen: (HomeScreenViewModel.HomeScreenState) -> Unit,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val homeScreenState by homeScreenViewModel.homeScreenState.collectAsState()
    val allScreensStateCollected = allScreensHomeState.collectAsState()

    LaunchedEffect(Unit) {
        if (allScreensHomeState.value.lastWatched == null) {
            homeScreenViewModel.getAllScreenData()
        }
    }
    LaunchedEffect(homeScreenState) {
        if (!homeScreenState.isLoading) {
            loadDataToHomeScreen(homeScreenState)
        }
    }

    val seriesList = allScreensStateCollected.value.seriesList
    val movieList = allScreensStateCollected.value.moviesList
    val lastWatchedMovieTime = allScreensStateCollected.value.lastWatchedMovieTime
    val movie = allScreensStateCollected.value.lastWatched?.movie
    val series = allScreensStateCollected.value.lastWatched?.serial
    val scrollState = rememberScrollState()

    var refreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(refreshing, { refreshing = true })
    LaunchedEffect(refreshing) {
        if (refreshing) {
            homeScreenViewModel.refreshLastWatched()
            delay(2000)
            refreshing = false
        }
    }

    SakhCastTheme {
        Box {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .pullRefresh(pullRefreshState)
                    .verticalScroll(scrollState)
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                if (allScreensStateCollected.value.isLoading) {
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
                } else {
                    if (movie != null && series != null) {
                        ContinueWatchView(
                            movie,
                            series,
                            lastWatchedMovieTime,
                            navigateToMovieByAlphaId,
                            navigateToSeriesById
                        )
                    }
                    if (seriesList != null) {
                        SeriesCategoryView(
                            seriesList,
                            navigateToSeriesById,
                            navigateToCatalogAllSeries
                        )
                    }
                    if (movieList != null) {
                        MovieCategoryView(
                            movieList,
                            navigateToMovieByAlphaId,
                            navigateToCatalogAllMovies
                        )
                    }
                }
            }
            PullRefreshIndicator(
                refreshing = refreshing,
                state = pullRefreshState,
                modifier = Modifier
                    .padding(paddingValues)
                    .align(Alignment.TopCenter)
                    .zIndex(1f)
            )
        }
    }
}
