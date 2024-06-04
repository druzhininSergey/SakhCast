package com.example.sakhcast.ui

import android.util.Log
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
import com.example.sakhcast.LOG_IN_SCREEN
import com.example.sakhcast.MOVIE_CATEGORY_SCREEN
import com.example.sakhcast.MOVIE_VIEW
import com.example.sakhcast.NOTIFICATION_SCREEN
import com.example.sakhcast.SEARCH_SCREEN
import com.example.sakhcast.SERIES_CATEGORY_SCREEN
import com.example.sakhcast.SERIES_VIEW
import com.example.sakhcast.ui.category_screens.MovieCategoryScreen
import com.example.sakhcast.ui.category_screens.MovieCategoryScreenViewModel
import com.example.sakhcast.ui.category_screens.SeriesCategoryScreen
import com.example.sakhcast.ui.category_screens.SeriesCategoryScreenViewModel
import com.example.sakhcast.ui.log_in_screen.LogInScreen
import com.example.sakhcast.ui.main_screens.catalog_screen.CatalogScreen
import com.example.sakhcast.ui.main_screens.catalog_screen.CatalogScreenViewModel
import com.example.sakhcast.ui.main_screens.favorites_screen.FavoritesScreen
import com.example.sakhcast.ui.main_screens.home_screen.HomeScreen
import com.example.sakhcast.ui.main_screens.home_screen.HomeScreenViewModel
import com.example.sakhcast.ui.main_screens.notifications_screen.NotificationScreen
import com.example.sakhcast.ui.main_screens.search_screen.SearchScreen
import com.example.sakhcast.ui.movie_series_view.MovieView
import com.example.sakhcast.ui.movie_series_view.SeriesView
import com.example.sakhcast.ui.profile_screen.ProfileScreenViewModel
import kotlinx.coroutines.coroutineScope

@Composable
fun NavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
) {
    val profileScreenViewModel: ProfileScreenViewModel = hiltViewModel()
    profileScreenViewModel.getIsLoggedInSharedPreferences()
    val profileScreenState = profileScreenViewModel.profileScreenState.observeAsState(
        ProfileScreenViewModel.ProfileScreenState()
    )
//    Log.i("!!!", "NavGraph profile state isLogged ${profileScreenState.value.isLogged}")
    NavHost(
        navController = navHostController,
        startDestination = if (!profileScreenState.value.isLogged!!) LOG_IN_SCREEN else HOME_SCREEN
    ) {
        composable(LOG_IN_SCREEN) {
            LogInScreen(navController = navHostController)
        }
        composable(HOME_SCREEN) {
            val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
            val homeScreenState by
            homeScreenViewModel.homeScreenState.observeAsState(HomeScreenViewModel.HomeScreenState())

            HomeScreen(paddingValues = paddingValues, homeScreenState)
        }
        composable(CATALOG_SCREEN) {
            val catalogScreenViewModel = hiltViewModel<CatalogScreenViewModel>()
            val catalogScreenState by
            catalogScreenViewModel.catalogScreenState.observeAsState(CatalogScreenViewModel.CatalogScreenState())

            CatalogScreen(paddingValues = paddingValues, navHostController, catalogScreenState)
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
            MovieView(paddingValues, navHostController)
        }
        composable(SERIES_VIEW) {
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
            val seriesCategoryScreenViewModel = hiltViewModel<SeriesCategoryScreenViewModel>()
            seriesCategoryScreenViewModel.getSelectedCategoryName(
                navHostController.currentBackStackEntry?.arguments?.getString(
                    "category"
                ) ?: "Все"
            )
            val seriesCategoryScreenState by seriesCategoryScreenViewModel.seriesCategoryScreenState.observeAsState(
                SeriesCategoryScreenViewModel.SeriesCategoryScreenState()
            )
            SeriesCategoryScreen(paddingValues, navHostController, seriesCategoryScreenState)
        }
    }
}