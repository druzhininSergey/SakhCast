package com.example.sakhcast.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sakhcast.CATALOG_SCREEN
import com.example.sakhcast.FAVORITES_SCREEN
import com.example.sakhcast.HOME_SCREEN
import com.example.sakhcast.LOG_IN_SCREEN
import com.example.sakhcast.MOVIE_CATEGORY_SCREEN
import com.example.sakhcast.MOVIE_VIEW
import com.example.sakhcast.NOTIFICATION_SCREEN
import com.example.sakhcast.SEARCH_SCREEN
import com.example.sakhcast.SERIES_CATEGORY_SCREEN
import com.example.sakhcast.SERIES_VIEW
import com.example.sakhcast.ui.category_screens.MovieCategoryScreen
import com.example.sakhcast.ui.category_screens.SeriesCategoryScreen
import com.example.sakhcast.ui.log_in_screen.LogInScreen
import com.example.sakhcast.ui.main_screens.catalog_screen.CatalogScreen
import com.example.sakhcast.ui.main_screens.favorites_screen.FavoritesScreen
import com.example.sakhcast.ui.main_screens.home_screen.HomeScreen
import com.example.sakhcast.ui.main_screens.notifications_screen.NotificationScreen
import com.example.sakhcast.ui.main_screens.search_screen.SearchScreen
import com.example.sakhcast.ui.movie_series_view.MovieView
import com.example.sakhcast.ui.movie_series_view.SeriesView

@Composable
fun NavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navHostController,
        startDestination = LOG_IN_SCREEN
    ) {
        composable(LOG_IN_SCREEN) {
            LogInScreen(navController = navHostController)
        }
        composable(HOME_SCREEN) {
            HomeScreen(paddingValues = paddingValues)
        }
        composable(CATALOG_SCREEN) {
            CatalogScreen(paddingValues = paddingValues, navHostController)
        }
        composable(FAVORITES_SCREEN) {
            FavoritesScreen(paddingValues = paddingValues)
        }
        composable(NOTIFICATION_SCREEN) {
            NotificationScreen(paddingValues = paddingValues)
        }
        composable(SEARCH_SCREEN) {
            SearchScreen()
        }
        composable(MOVIE_VIEW) {
            MovieView(paddingValues)
        }
        composable(SERIES_VIEW) {
            SeriesView(paddingValues)
        }
        composable(MOVIE_CATEGORY_SCREEN) {
            MovieCategoryScreen(paddingValues, navHostController)
        }
        composable(SERIES_CATEGORY_SCREEN) {
            SeriesCategoryScreen(paddingValues, navHostController)
        }
    }
}