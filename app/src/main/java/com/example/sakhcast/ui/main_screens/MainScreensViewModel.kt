package com.example.sakhcast.ui.main_screens

import androidx.lifecycle.ViewModel
import com.example.sakhcast.ui.main_screens.favorites_screen.FavoritesScreenViewModel
import com.example.sakhcast.ui.main_screens.home_screen.HomeScreenViewModel
import com.example.sakhcast.ui.main_screens.notifications_screen.NotificationScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainScreensViewModel @Inject constructor(
) : ViewModel() {

    private val _homeScreenState = MutableStateFlow(HomeScreenViewModel.HomeScreenState())
    val homeScreenState = _homeScreenState.asStateFlow()

    private val _favoritesScreenState =
        MutableStateFlow(FavoritesScreenViewModel.FavoritesScreenState())
    val favoritesScreenState = _favoritesScreenState.asStateFlow()

    private val _notificationScreenState =
        MutableStateFlow(NotificationScreenViewModel.NotificationScreenState())
    val notificationScreenState = _notificationScreenState.asStateFlow()

    fun loadDataToHomeScreen(homeScreenStateUpdated: HomeScreenViewModel.HomeScreenState) {
        _homeScreenState.update { homeScreenStateUpdated }
    }

    fun loadDataToFavoritesScreen(favoritesScreenStateUpdated: FavoritesScreenViewModel.FavoritesScreenState) {
        _favoritesScreenState.update { favoritesScreenStateUpdated }
    }

    fun loadDataToNotificationsScreen(notificationsScreenStateUpdated: NotificationScreenViewModel.NotificationScreenState) {
        _notificationScreenState.update { notificationsScreenStateUpdated }
    }
}