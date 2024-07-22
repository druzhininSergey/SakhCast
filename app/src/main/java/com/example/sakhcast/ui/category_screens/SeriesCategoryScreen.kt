package com.example.sakhcast.ui.category_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.sakhcast.Dimens
import com.example.sakhcast.model.SeriesCard
import kotlinx.coroutines.flow.filterNotNull

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeriesCategoryScreen(
    paddingValues: PaddingValues,
    categoryName: String,
    name: String,
    navigateUp: () -> Boolean,
    navigateToSeriesById: (String) -> Unit,
    seriesCategoryScreenViewModel: SeriesCategoryScreenViewModel = hiltViewModel()
) {

    val seriesPagingData: LazyPagingItems<SeriesCard>? =
        seriesCategoryScreenViewModel.seriesCategoryScreenState.value?.seriesPagingData?.collectAsLazyPagingItems()

    LaunchedEffect(categoryName) {
        seriesCategoryScreenViewModel.initCategory(categoryName)
    }
    val lazyGridState = rememberLazyGridState()
    val isDataLoaded = remember { mutableStateOf(false) }

    LaunchedEffect(seriesPagingData) {
        snapshotFlow { seriesPagingData?.itemCount }
            .filterNotNull()
            .collect { itemCount ->
                isDataLoaded.value = itemCount > 0
            }
    }

    val categoryNameTitle = if (categoryName.endsWith(".company")) name
    else if (categoryName.endsWith(".favorite")) name
    else categoryName.replaceFirstChar { it.uppercase() }

    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    textAlign = TextAlign.Center,
                    text = categoryNameTitle
                )
            },
            navigationIcon = {
                IconButton(onClick = { navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
        )
        seriesPagingData?.let { pagingItems ->
            if (isDataLoaded.value) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .padding(bottom = paddingValues.calculateBottomPadding())
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Dimens.mainPadding),
                    verticalArrangement = Arrangement.spacedBy(Dimens.mainPadding),
                    contentPadding = PaddingValues(Dimens.mainPadding),
                    state = lazyGridState
                ) {
                    items(pagingItems.itemCount, key = { index ->
                        pagingItems[index]?.id ?: index.toString()
                    }) { index ->
                        pagingItems[index]?.let {
                            if (categoryName.endsWith(".favorite")) {
                                SeriesCategoryCardItem(
                                    seriesCard = it,
                                    isFavoriteScreen = true,
                                    navigateToSeriesById = navigateToSeriesById
                                )
                            } else {
                                SeriesCategoryCardItem(
                                    seriesCard = it,
                                    navigateToSeriesById = navigateToSeriesById
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}