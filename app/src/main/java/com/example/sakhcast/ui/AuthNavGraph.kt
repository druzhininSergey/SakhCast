package com.example.sakhcast.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.sakhcast.SERIES_VIEW
import com.example.sakhcast.ui.category_screens.MovieCategoryScreen
import com.example.sakhcast.ui.category_screens.SeriesCategoryScreen
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

@Composable
fun AuthNavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
) {
    val navigateUp = { navHostController.navigateUp() }
    val navigate = { route: String -> navHostController.navigate(route) }

    val navigateToMovieByAlphaId = { movieAlphaId: String -> navigate("$MOVIE_VIEW/$movieAlphaId") }
    val navigateToSeriesById = { seriesId: String -> navigate("$SERIES_VIEW/$seriesId") }
    val navigateToCatalogAllSeries = { navigate("$SERIES_CATEGORY_SCREEN/Все") }
    val navigateToCatalogAllMovies = { navigate("$MOVIE_CATEGORY_SCREEN/Все/{}") }
    val navigateToSeriesCategoryScreen = { categoryName: String -> navigate("$SERIES_CATEGORY_SCREEN/$categoryName") }
    val navigateToMoviesCategoryScreen = { categoryName: String -> navigate("$MOVIE_CATEGORY_SCREEN/$categoryName/{}") }
    val navigateToMoviePlayer = { hlsToSend: String, titleToSend: String, positionToSend: Int, alphaIdToSend: String ->
        navigate("$PLAYER/$hlsToSend/$titleToSend/$positionToSend/$alphaIdToSend")
    }
    val navigateToMovieCategoriesByGenresId = { genresName: String, genresId: String ->
        navigate("$MOVIE_CATEGORY_SCREEN/$genresName/$genresId")
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
                navigateToCatalogAllMovies = navigateToCatalogAllMovies
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
            )
        }
        composable(NOTIFICATION_SCREEN) {
            val notificationScreenViewModel: NotificationScreenViewModel = hiltViewModel()
            val notificationScreenState =
                notificationScreenViewModel.notificationScreenState.observeAsState(
                    NotificationScreenViewModel.NotificationScreenState()
                )
            NotificationScreen(paddingValues, notificationScreenState)
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
            )
        }
        composable("$MOVIE_CATEGORY_SCREEN/{category}/{genresId}") { backStackEntry ->
            val searchCategoryName = backStackEntry.arguments?.getString("category") ?: "Все"
            val searchGenreId = backStackEntry.arguments?.getString("genresId")
            MovieCategoryScreen(
                paddingValues = paddingValues,
                searchCategoryName = searchCategoryName,
                searchGenreId = searchGenreId,
                navigateUp = navigateUp,
                navigateToMovieByAlphaId = navigateToMovieByAlphaId
            )
        }
        composable("$SERIES_CATEGORY_SCREEN/{category}") { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("category") ?: "Все"
            SeriesCategoryScreen(
                paddingValues = paddingValues,
                categoryName = categoryName,
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
    }
}