package com.example.sakhcast.ui.main_screens.favorites_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun FavoritesScreen(
    paddingValues: PaddingValues,
    navigateToMovieByAlphaId: (String) -> Unit,
    navigateToSeriesById: (String) -> Unit,
    navigateToSeriesCategoryByType: (String, String) -> Unit,
    navigateToMovieCategoriesByGenresId: (String, String) -> Unit,
    allScreensFavoriteState: StateFlow<FavoritesScreenViewModel.FavoritesScreenState>,
    loadDataToHomeScreen: (FavoritesScreenViewModel.FavoritesScreenState) -> Unit,
    favoritesScreenViewModel: FavoritesScreenViewModel = hiltViewModel()
) {
    val favoritesScreenState by favoritesScreenViewModel.favoritesScreenState.collectAsState()
    val allScreensStateCollected = allScreensFavoriteState.collectAsState()

    LaunchedEffect(Unit) {
        if (allScreensStateCollected.value.movieCardsWatched == null) {
            favoritesScreenViewModel.getAllContent()
        }
    }

    LaunchedEffect(favoritesScreenState) {
        if (!favoritesScreenState.isLoading) {
            loadDataToHomeScreen(favoritesScreenState)
        }
    }

    val seriesCardWatching = allScreensStateCollected.value.seriesCardWatching
    val seriesCardWillWatch = allScreensStateCollected.value.seriesCardWillWatch
    val seriesCardFinishedWatching =
        allScreensStateCollected.value.seriesCardFinishedWatching
    val seriesCardWatched = allScreensStateCollected.value.seriesCardWatched
    val movieCardsWillWatch = allScreensStateCollected.value.movieCardsWillWatch
    val movieCardsWatched = allScreensStateCollected.value.movieCardsWatched

    val tabList = listOf("Сериалы", "Фильмы")
    var tabIndex by remember {
        mutableIntStateOf(0)
    }
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState {
        tabList.size
    }
    var refreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(refreshing, { refreshing = true })
    LaunchedEffect(refreshing) {
        if (refreshing) {
            favoritesScreenViewModel.getAllContent()
            delay(2000)
            refreshing = false
        }
    }
    LaunchedEffect(tabIndex) {
        pagerState.animateScrollToPage(tabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        tabIndex = pagerState.currentPage
    }

    Box {
        Column(modifier = Modifier.padding(paddingValues).pullRefresh(pullRefreshState)) {
            TabRow(
                modifier = Modifier,
                selectedTabIndex = tabIndex,
                containerColor = MaterialTheme.colorScheme.primary,
                divider = { HorizontalDivider(color = MaterialTheme.colorScheme.primary) },
                indicator = { tabPositions ->
                    SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            ) {
                tabList.forEachIndexed { index, text ->
                    Tab(
                        selected = index == tabIndex,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(
                                text = text,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        },
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false
            ) { index ->
                if (index == 0)
                    SeriesPage(
                        seriesCardWatching,
                        seriesCardWillWatch,
                        seriesCardFinishedWatching,
                        seriesCardWatched,
                        navigateToSeriesById,
                        navigateToSeriesCategoryByType
                    )
                else MoviesPage(
                    movieCardsWillWatch,
                    movieCardsWatched,
                    navigateToMovieByAlphaId,
                    navigateToMovieCategoriesByGenresId
                )
            }
        }
        Box (modifier = Modifier.fillMaxSize()){
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