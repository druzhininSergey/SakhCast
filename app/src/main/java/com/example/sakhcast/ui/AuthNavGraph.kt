package com.example.sakhcast.ui

import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sakhcast.CATALOG_SCREEN
import com.example.sakhcast.FAVORITES_SCREEN
import com.example.sakhcast.HOME_SCREEN
import com.example.sakhcast.MOVIE_CATEGORY_SCREEN
import com.example.sakhcast.MOVIE_VIEW
import com.example.sakhcast.NOTIFICATION_SCREEN
import com.example.sakhcast.PLAYER
import com.example.sakhcast.SEARCH_SCREEN
import com.example.sakhcast.SERIES_CATEGORY_SCREEN
import com.example.sakhcast.SERIES_PLAYER
import com.example.sakhcast.SERIES_VIEW
import com.example.sakhcast.ui.category_screens.MovieCategoryScreen
import com.example.sakhcast.ui.category_screens.SeriesCategoryScreen
import com.example.sakhcast.ui.main_screens.MainScreensViewModel
import com.example.sakhcast.ui.main_screens.catalog_screen.CatalogScreen
import com.example.sakhcast.ui.main_screens.catalog_screen.CatalogScreenViewModel
import com.example.sakhcast.ui.main_screens.favorites_screen.FavoritesScreen
import com.example.sakhcast.ui.main_screens.home_screen.HomeScreen
import com.example.sakhcast.ui.main_screens.notifications_screen.NotificationScreen
import com.example.sakhcast.ui.main_screens.notifications_screen.NotificationScreenViewModel
import com.example.sakhcast.ui.main_screens.search_screen.SearchScreen
import com.example.sakhcast.ui.movie_series_view.MovieView
import com.example.sakhcast.ui.movie_series_view.SeriesView
import com.example.sakhcast.ui.player.Player2
import com.example.sakhcast.ui.series_player.SeriesPlayer
import com.example.sakhcast.ui.series_player.SeriesPlayerViewModel

@Composable
fun AuthNavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    mainScreensViewModel: MainScreensViewModel = hiltViewModel()
) {
    val navigateUp = { navHostController.navigateUp() }
    val navigate = { route: String -> navHostController.navigate(route) }

    val navigateToMovieByAlphaId = { movieAlphaId: String -> navigate("$MOVIE_VIEW/$movieAlphaId") }
    val navigateToSeriesById = { seriesId: String -> navigate("$SERIES_VIEW/$seriesId") }
    val navigateToCatalogAllSeries = { navigate("$SERIES_CATEGORY_SCREEN/Все/{}") }
    val navigateToCatalogAllMovies = { navigate("$MOVIE_CATEGORY_SCREEN/Все/{}") }
    val navigateToSeriesCategoryScreen =
        { categoryName: String -> navigate("$SERIES_CATEGORY_SCREEN/$categoryName/{}") }
    val navigateToMoviesCategoryScreen =
        { categoryName: String -> navigate("$MOVIE_CATEGORY_SCREEN/$categoryName/{}") }
    val navigateToMoviePlayer =
        { hlsToSend: String, titleToSend: String, positionToSend: Int, alphaIdToSend: String ->
            navigate("$PLAYER/$hlsToSend/$titleToSend/$positionToSend/$alphaIdToSend")
        }
    val navigateToMovieCategoriesByGenresId = { genresName: String, genresId: String ->
        navigate("$MOVIE_CATEGORY_SCREEN/$genresName/$genresId")
    }
    val navigateToSeriesCategoryByType =
        { type: String, name: String -> navigate("$SERIES_CATEGORY_SCREEN/$type/$name") }
    val navigateToSeriesPlayer = {
            seasonId: String,
            seriesTitle: String,
            episodeChosenIndex: String,
            rgChosen: String,
        ->
        navigate("$SERIES_PLAYER/$seasonId/$seriesTitle/$episodeChosenIndex/$rgChosen")
    }

    NavHost(
        navController = navHostController,
        startDestination = HOME_SCREEN
    ) {
        composable(HOME_SCREEN) {
            HomeScreen(
                paddingValues = paddingValues,
                navigateToMovieByAlphaId = navigateToMovieByAlphaId,
                navigateToSeriesById = navigateToSeriesById,
                navigateToCatalogAllSeries = navigateToCatalogAllSeries,
                navigateToCatalogAllMovies = navigateToCatalogAllMovies,
                allScreensHomeState = mainScreensViewModel.homeScreenState,
                loadDataToHomeScreen = mainScreensViewModel::loadDataToHomeScreen,
            )
        }
        composable(CATALOG_SCREEN) {
            val catalogScreenViewModel = hiltViewModel<CatalogScreenViewModel>()
            val catalogScreenState by
            catalogScreenViewModel.catalogScreenState.observeAsState(CatalogScreenViewModel.CatalogScreenState())

            CatalogScreen(
                paddingValues = paddingValues,
                navigateToSeriesCategoryScreen = navigateToSeriesCategoryScreen,
                navigateToMoviesCategoryScreen = navigateToMoviesCategoryScreen,
                catalogScreenState = catalogScreenState
            )
        }
        composable(FAVORITES_SCREEN) {
            FavoritesScreen(
                paddingValues = paddingValues,
                navigateToMovieByAlphaId = navigateToMovieByAlphaId,
                navigateToSeriesById = navigateToSeriesById,
                navigateToSeriesCategoryByType = navigateToSeriesCategoryByType,
                navigateToMovieCategoriesByGenresId = navigateToMovieCategoriesByGenresId,
                allScreensFavoriteState = mainScreensViewModel.favoritesScreenState,
                loadDataToHomeScreen = mainScreensViewModel::loadDataToFavoritesScreen,
            )
        }
        composable(NOTIFICATION_SCREEN) {
            val notificationScreenViewModel: NotificationScreenViewModel = hiltViewModel()
            val notificationScreenState by notificationScreenViewModel.notificationScreenState.collectAsState()
            val allScreensNotificationsState by mainScreensViewModel.notificationScreenState.collectAsState()
            LaunchedEffect(Unit) {
                if (allScreensNotificationsState.notificationsList == null) {
                    notificationScreenViewModel.getNotifications()
                }
            }
            LaunchedEffect(notificationScreenState) {
                if (!notificationScreenState.isLoading) {
                    mainScreensViewModel.loadDataToNotificationsScreen(notificationScreenState)
                }
            }

            NotificationScreen(
                paddingValues,
                allScreensNotificationsState,
                notificationScreenViewModel::makeAllNotificationsRead,
                notificationScreenViewModel::getNotifications,
                navigateToSeriesById
            )
        }
        composable(SEARCH_SCREEN) {
            SearchScreen(
                paddingValues = paddingValues,
                navigateToMovieByAlphaId = navigateToMovieByAlphaId,
                navigateToSeriesById = navigateToSeriesById
            )
        }
        composable("$MOVIE_VIEW/{movieId}") { backStackEntry ->
            val alphaId = backStackEntry.arguments?.getString("movieId")
            MovieView(
                paddingValues = paddingValues,
                alphaId = alphaId,
                navigateToMoviePlayer = navigateToMoviePlayer,
                navigateToMovieByAlphaId = navigateToMovieByAlphaId,
                navigateToMovieCategoriesByGenresId = navigateToMovieCategoriesByGenresId,
                navigateUp = navigateUp
            )
        }
        composable("$SERIES_VIEW/{seriesId}") { backStackEntry ->
            val seriesId = backStackEntry.arguments?.getString("seriesId")?.toIntOrNull()
            SeriesView(
                paddingValues = paddingValues,
                navigateUp = navigateUp,
                seriesId = seriesId,
                navigateToSeriesCategoryByCompany = navigateToSeriesCategoryByType,
                navigateToSeriesCategoryScreen = navigateToSeriesCategoryScreen,
                navigateToSeriesPlayer = navigateToSeriesPlayer
            )
        }
        composable("$MOVIE_CATEGORY_SCREEN/{category}/{genresId}") { backStackEntry ->
            val searchCategoryNameUri = backStackEntry.arguments?.getString("category") ?: "Все"
            val searchCategoryName = Uri.decode(searchCategoryNameUri)
            val searchGenreId = backStackEntry.arguments?.getString("genresId")
            MovieCategoryScreen(
                paddingValues = paddingValues,
                searchCategoryName = searchCategoryName,
                searchGenreId = searchGenreId,
                navigateUp = navigateUp,
                navigateToMovieByAlphaId = navigateToMovieByAlphaId
            )
        }
        composable("$SERIES_CATEGORY_SCREEN/{category}/{name}") { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("category") ?: "Все"
            val name = backStackEntry.arguments?.getString("name") ?: ""
            SeriesCategoryScreen(
                paddingValues = paddingValues,
                categoryName = categoryName,
                name = name,
                navigateUp = navigateUp,
                navigateToSeriesById = navigateToSeriesById,
            )
        }
        composable("$PLAYER/{hls}/{title}/{position}/{movieAlphaId}") { backStackEntry ->
            val hls = backStackEntry.arguments?.getString("hls") ?: ""
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val positionStr = backStackEntry.arguments?.getString("position") ?: "0"
            val position = positionStr.toInt()
            val movieAlphaId = backStackEntry.arguments?.getString("movieAlphaId") ?: ""
            Player2(
                hls = hls,
                title = title,
                position = position,
                movieAlphaId = movieAlphaId,
                navigateUp = navigateUp
            )
        }
        composable("$SERIES_PLAYER/{seasonId}/{seriesTitle}/{episodeChosenIndex}/{rgChosen}") { backStackEntry ->
            val seasonId = backStackEntry.arguments?.getString("seasonId") ?: ""
            val seriesTitle = backStackEntry.arguments?.getString("seriesTitle") ?: ""
            val episodeChosenIndex =
                backStackEntry.arguments?.getString("episodeChosenIndex") ?: "1"
            val episodeChosenIndexInt = episodeChosenIndex.toInt()
            val rgChosen = backStackEntry.arguments?.getString("rgChosen") ?: ""

            val initialized = rememberSaveable { mutableStateOf(false) }
            val seriesPlayerViewModel: SeriesPlayerViewModel = hiltViewModel()
            LaunchedEffect(seasonId) {
                if (!initialized.value) {
                    seriesPlayerViewModel.setSeriesData(
                        seasonId,
                        seriesTitle,
                        episodeChosenIndexInt,
                        rgChosen
                    )
                    seriesPlayerViewModel.getPlaylist(seasonId, rgChosen)
                    initialized.value = true
                }
            }
            val isPlayListLoaded by seriesPlayerViewModel.isPlaylistLoaded.collectAsState()
            val isDataLoaded by seriesPlayerViewModel.isDataLoaded.collectAsState()
            val seriesState by seriesPlayerViewModel.seriesWatchState.collectAsState()

            LaunchedEffect(isDataLoaded) {
                if (isDataLoaded) seriesPlayerViewModel.startPlayer()
            }

            SeriesPlayer(
                navigateUp = navigateUp,
                isPlayListLoaded,
                isDataLoaded,
                seriesState,
                seriesPlayerViewModel.player,
                seriesPlayerViewModel::onEpisodeChanged,
            )
        }
    }
}