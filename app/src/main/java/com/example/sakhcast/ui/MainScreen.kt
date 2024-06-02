package com.example.sakhcast.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sakhcast.LOG_IN_SCREEN
import com.example.sakhcast.MOVIE_CATEGORY_SCREEN
import com.example.sakhcast.SERIES_CATEGORY_SCREEN
import com.example.sakhcast.data.UserSample
import com.example.sakhcast.ui.top_bottom_bars.TopAppCategoryBar
import com.example.sakhcast.ui.top_bottom_bars.bottom_app_bar.BottomBar
import com.example.sakhcast.ui.top_bottom_bars.top_app_bar.TopBar

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route
    var showBars by rememberSaveable { mutableStateOf(currentDestination != LOG_IN_SCREEN) }
    var showTopAppCategoryBar by rememberSaveable {
        mutableStateOf(currentDestination == SERIES_CATEGORY_SCREEN || currentDestination == MOVIE_CATEGORY_SCREEN)
    }

    LaunchedEffect(currentDestination) {
        showTopAppCategoryBar =
            currentDestination == SERIES_CATEGORY_SCREEN || currentDestination == MOVIE_CATEGORY_SCREEN
    }
    LaunchedEffect(currentDestination) {
        showBars = currentDestination != LOG_IN_SCREEN
    }

    Scaffold(
        topBar = {
            when {
                showTopAppCategoryBar -> TopAppCategoryBar(navController = navController)
                showBars -> TopBar(UserSample.getUserInfo())
            }
        },
        bottomBar = { if (showBars) BottomBar(navController) },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        NavGraph(
            navHostController = navController,
            paddingValues = it,
        )
    }
}