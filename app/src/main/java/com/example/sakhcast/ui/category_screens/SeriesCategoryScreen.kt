package com.example.sakhcast.ui.category_screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sakhcast.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SeriesCategoryScreen(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    seriesCategoryScreenViewModel: SeriesCategoryScreenViewModel = hiltViewModel()
) {
    val categoryName = navHostController.currentBackStackEntry?.arguments?.getString(
        "category"
    ) ?: "Все"
    val gridState = rememberLazyGridState()
    var page by remember { mutableIntStateOf(0) }
    Log.e("!!!", "page = $page")
    LaunchedEffect(page, categoryName) {
        seriesCategoryScreenViewModel.initCategory(page, categoryName)
    }
    val seriesCategoryScreenState by seriesCategoryScreenViewModel.seriesCategoryScreenState.observeAsState(
        SeriesCategoryScreenViewModel.SeriesCategoryScreenState()
    )
    val seriesList = seriesCategoryScreenState.seriesList

    Log.e("!!!", "seriesList = $seriesList")
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    textAlign = TextAlign.Center,
                    text = categoryName
                )
            },
            navigationIcon = {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(bottom = paddingValues.calculateBottomPadding())
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimens.mainPadding),
            verticalArrangement = Arrangement.spacedBy(Dimens.mainPadding),
            contentPadding = PaddingValues(Dimens.mainPadding),
            state = gridState,
        ) {
            if (seriesList != null)
                itemsIndexed(seriesList.items) { _, series ->
                    SeriesCategoryCardItem(series, navHostController)
                }
        }
    }
    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val lastVisibleItemIndex = visibleItems.lastOrNull()?.index
                val totalItemsCount = seriesList?.items?.size ?: 0

                // Лог для проверки индексов и количества элементов
                Log.e("!!!", "lastVisibleItemIndex = $lastVisibleItemIndex, totalItemsCount = $totalItemsCount")

                if (lastVisibleItemIndex == totalItemsCount - 1 && totalItemsCount > 0) {
                    page += 1
                    Log.e("!!!", "Page incremented to $page")
                }
            }
    }
}