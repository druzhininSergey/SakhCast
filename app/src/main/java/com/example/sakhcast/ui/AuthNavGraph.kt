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
import com.example.sakhcast.SEARCH_SCREEN
import com.example.sakhcast.SERIES_CATEGORY_SCREEN
import com.example.sakhcast.SERIES_VIEW
import com.example.sakhcast.ui.category_screens.MovieCategoryScreen
import com.example.sakhcast.ui.category_screens.MovieCategoryScreenViewModel
import com.example.sakhcast.ui.category_screens.SeriesCategoryScreen
import com.example.sakhcast.ui.main_screens.catalog_screen.CatalogScreen
import com.example.sakhcast.ui.main_screens.catalog_screen.CatalogScreenViewModel
import com.example.sakhcast.ui.main_screens.favorites_screen.FavoritesScreen
import com.example.sakhcast.ui.main_screens.favorites_screen.FavoritesScreenViewModel
import com.example.sakhcast.ui.main_screens.home_screen.HomeScreen
import com.example.sakhcast.ui.main_screens.notifications_screen.NotificationScreen
import com.example.sakhcast.ui.main_screens.notifications_screen.NotificationScreenViewModel
import com.example.sakhcast.ui.main_screens.search_screen.SearchScreen
import com.example.sakhcast.ui.movie_series_view.MovieView
import com.example.sakhcast.ui.movie_series_view.SeriesView

@Composable
fun AuthNavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
) {

    NavHost(
        navController = navHostController,
        startDestination = HOME_SCREEN
    ) {

        composable(HOME_SCREEN) {
            HomeScreen(
                paddingValues = paddingValues, navHostController = navHostController
            )
        }
        composable(CATALOG_SCREEN) {
            val catalogScreenViewModel = hiltViewModel<CatalogScreenViewModel>()
            val catalogScreenState by
            catalogScreenViewModel.catalogScreenState.observeAsState(CatalogScreenViewModel.CatalogScreenState())

            CatalogScreen(paddingValues = paddingValues, navHostController, catalogScreenState)
        }
        composable(FAVORITES_SCREEN) {
            val favoritesScreenViewModel: FavoritesScreenViewModel = hiltViewModel()
            val favoritesScreenState = favoritesScreenViewModel.favoritesScreenState.observeAsState(
                FavoritesScreenViewModel.FavoritesScreenState()
            )
            FavoritesScreen(paddingValues, favoritesScreenState, navHostController)
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
            SearchScreen()
        }
        composable("$MOVIE_VIEW/{movieId}") {
            MovieView(paddingValues, navHostController)
        }
        composable("$SERIES_VIEW/{seriesId}") {
            SeriesView(paddingValues, navHostController)
        }
        composable("$MOVIE_CATEGORY_SCREEN/{category}") {
            val movieCategoryScreenViewModel = hiltViewModel<MovieCategoryScreenViewModel>()
            movieCategoryScreenViewModel.getSelectedCategoryName(
                navHostController.currentBackStackEntry?.arguments?.getString(
                    "category"
                ) ?: "Все"
            )
            val moviesCategoryScreenState by movieCategoryScreenViewModel.moviesCategoryScreenState.observeAsState(
                MovieCategoryScreenViewModel.MoviesCategoryScreenState()
            )
            MovieCategoryScreen(paddingValues, navHostController, moviesCategoryScreenState)
        }
        composable("$SERIES_CATEGORY_SCREEN/{category}") {

            SeriesCategoryScreen(paddingValues, navHostController)
        }
    }
}