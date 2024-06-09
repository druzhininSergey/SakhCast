package com.example.sakhcast.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sakhcast.LOG_IN_SCREEN
import com.example.sakhcast.MOVIE_CATEGORY_SCREEN
import com.example.sakhcast.MOVIE_VIEW
import com.example.sakhcast.SERIES_CATEGORY_SCREEN
import com.example.sakhcast.SERIES_VIEW
import com.example.sakhcast.ui.log_in_screen.LogInScreenViewModel
import com.example.sakhcast.ui.top_bottom_bars.bottom_app_bar.BottomBar
import com.example.sakhcast.ui.top_bottom_bars.top_app_bar.TopBar

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route
    val backStackState = navController.currentBackStackEntryAsState().value
    val logInScreenViewModel: LogInScreenViewModel = hiltViewModel()
    val loginScreenState = logInScreenViewModel.userDataState.observeAsState(
        LogInScreenViewModel.UserDataState()
    )
    logInScreenViewModel.checkLoggedUser()
    val isLogged = loginScreenState.value.isLogged
    val user = loginScreenState.value.curentUser


    val isTopBarVisible = remember(key1 = backStackState) {
        currentDestination != "$SERIES_CATEGORY_SCREEN/{category}" &&
                currentDestination != "$MOVIE_CATEGORY_SCREEN/{category}" &&
                currentDestination != LOG_IN_SCREEN &&
                currentDestination != MOVIE_VIEW &&
                currentDestination != SERIES_VIEW
    }
    val isBottomBarVisible = remember(key1 = backStackState) {
        currentDestination != LOG_IN_SCREEN &&
                currentDestination != MOVIE_VIEW &&
                currentDestination != SERIES_VIEW
    }

    Scaffold(
        topBar = { if (isTopBarVisible) TopBar(user) },
        bottomBar = { if (isBottomBarVisible) BottomBar(navController) },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        NavGraph(
            navHostController = navController,
            paddingValues = it,
            isLogged,
        )
    }
}