package com.example.sakhcast.ui.top_bottom_bars.bottom_app_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
) {
    data object HomeScreen : BottomItem("Главная", Icons.Sharp.Home, "home_screen")
    data object CatalogScreen : BottomItem("Каталог", Icons.Filled.Menu, "catalog_screen")
    data object FavoritesScreen : BottomItem("Избранное", Icons.Filled.Star, "favorites_screen")
    data object NotificationsScreen : BottomItem("Уведомления", Icons.Filled.Notifications, "notifications_screen")
    data object SearchScreen : BottomItem("Поиск", Icons.Sharp.Search, "search_screen")
}

