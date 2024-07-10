package com.example.sakhcast.ui.main_screens.catalog_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sakhcast.Dimens
import com.example.sakhcast.Genres

@Composable
fun CatalogList(
    categories: List<String>,
    tabIndex: Int,
    navigateToMoviesCategoryScreen: (String) -> Unit,
    navigateToSeriesCategoryScreen: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        itemsIndexed(categories) { index, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        if (tabIndex == 0) {
                            if (index == 0) {
                                expanded = true
                            } else navigateToSeriesCategoryScreen(item)
                        } else {
                            if (index == 0) {
                                expanded = true
                            } else navigateToMoviesCategoryScreen(item)
                        }
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(
                        start = Dimens.mainPaddingHalf,
                        end = Dimens.mainPaddingHalf
                    ),
                    text = item,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            if (index != categories.size - 1)
                HorizontalDivider(
                    modifier = Modifier
                        .padding(start = Dimens.mainPaddingHalf, end = Dimens.mainPaddingHalf),
                    thickness = 1.dp,
                    color = Color.White
                )
        }
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        if (tabIndex == 0){
            Genres.seriesGenres.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        selectedCategory = category
                        navigateToSeriesCategoryScreen(category)
                    }
                ) {
                    Text(text = category)
                }
            }
        } else {
            Genres.movieGenresMap.keys.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        selectedCategory = category
                        navigateToMoviesCategoryScreen(category)
                    }
                ) {
                    Text(text = category)
                }
            }
        }

    }
}