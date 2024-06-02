package com.example.sakhcast.ui.category_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.sakhcast.Dimens
import com.example.sakhcast.data.Samples


@Preview(showBackground = true)
@Composable
fun MovieCategoryScreen(paddingValues: PaddingValues, navHostController: NavHostController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.mainPadding),
        verticalArrangement = Arrangement.spacedBy(Dimens.mainPadding),
        contentPadding = PaddingValues(Dimens.mainPadding)
    ) {
        itemsIndexed(Samples.getAllMovies()) { _, movie ->
            MovieCategoryCardItem(movie, navHostController)
        }
    }
}
