package com.example.sakhcast.ui.category_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.sakhcast.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SeriesCategoryScreen(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    seriesCategoryScreenState: SeriesCategoryScreenViewModel.SeriesCategoryScreenState
) {
    val categoryName = seriesCategoryScreenState.categoryName
    val seriesList = seriesCategoryScreenState.seriesList
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    textAlign = TextAlign.Center,
                    text = categoryName
                )
            },
            navigationIcon = {
                IconButton(onClick = { navHostController.popBackStack() }) {
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
            contentPadding = PaddingValues(Dimens.mainPadding)
        ) {
            if (seriesList != null)
                itemsIndexed(seriesList.items) { _, series ->
                    SeriesCategoryCardItem(series, navHostController)
                }
        }
    }
}