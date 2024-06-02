package com.example.sakhcast.ui.top_bottom_bars.bottom_app_bar

import androidx.compose.foundation.background
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavHostController) {
    val listItems = listOf(
        BottomItem.HomeScreen,
        BottomItem.CatalogScreen,
        BottomItem.FavoritesScreen,
        BottomItem.NotificationsScreen,
        BottomItem.SearchScreen
    )
    BottomAppBar(modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val curentRoute = backStackEntry?.destination?.route
        NavigationBar {
            listItems.forEach { item ->

                NavigationBarItem(
                    selected = curentRoute == item.route,
                    onClick = { navController.navigate(item.route) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 9.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                )
            }
        }
    }
}