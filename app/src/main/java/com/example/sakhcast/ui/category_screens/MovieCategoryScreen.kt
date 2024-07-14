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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.sakhcast.Dimens
import com.example.sakhcast.model.MovieCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCategoryScreen(
    paddingValues: PaddingValues,
    searchCategoryName: String?,
    searchGenreId: String?,
    navigateUp: () -> Boolean,
    navigateToMovieByAlphaId: (String) -> Unit,
    movieCategoryScreenViewModel: MovieCategoryScreenViewModel = hiltViewModel()
) {
    val moviePagingData: LazyPagingItems<MovieCard>? =
        movieCategoryScreenViewModel.moviesCategoryScreenState.value?.moviesPagingData?.collectAsLazyPagingItems()
    LaunchedEffect(searchCategoryName, searchGenreId) {
        if (searchGenreId == null || searchGenreId == "{}")
            movieCategoryScreenViewModel.initCategory(searchCategoryName)
        else movieCategoryScreenViewModel.initCategory(searchGenreId)
    }
    val lazyGridState = rememberLazyGridState()

    Column {
        CenterAlignedTopAppBar(
            title = {
                if (searchCategoryName != null)
                    Text(
                        textAlign = TextAlign.Center,
                        text = searchCategoryName.replaceFirstChar { it.uppercaseChar() }
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

        moviePagingData?.let { pagingItems ->
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
                        MovieCategoryCardItem(
                            it,
                            navigateToMovieByAlphaId
                        )
                    }
                }
            }
        }
    }
}
