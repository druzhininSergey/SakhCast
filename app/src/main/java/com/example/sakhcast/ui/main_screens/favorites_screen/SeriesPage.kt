package com.example.sakhcast.ui.main_screens.favorites_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sakhcast.Colors
import com.example.sakhcast.Dimens
import com.example.sakhcast.model.SeriesList
import com.example.sakhcast.ui.main_screens.home_screen.series.SeriesItemView

@Composable
fun SeriesPage(
    seriesCardWatching: SeriesList?,
    seriesCardWillWatch: SeriesList?,
    seriesCardFinishedWatching: SeriesList?,
    seriesCardWatched: SeriesList?,
    navigateToSeriesById: (String) -> Unit,
    navigateToSeriesCategoryByType: (String, String) -> Unit,
) {
    val categoryNames = mapOf(
        "Cмотрю" to "watching",
        "Буду смотреть" to "will",
        "Перестал" to "stopped",
        "Досмотрел" to "watched"
    )
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        categoryNames.entries.forEachIndexed { index, category ->
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = Dimens.mainPadding,
                        vertical = Dimens.mainPadding
                    )
                    .clickable {
                        navigateToSeriesCategoryByType(
                            "${category.value}.favorite",
                            category.key
                        )
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = category.key,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Icon(
                    modifier = Modifier.padding(top = 4.dp, start = 5.dp),
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null
                )
            }
            if (
                seriesCardWatching != null &&
                seriesCardWillWatch != null &&
                seriesCardFinishedWatching != null &&
                seriesCardWatched != null
            ) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = Dimens.mainPadding),
                    horizontalArrangement = Arrangement.spacedBy(Dimens.mainPadding)
                ) {

                    itemsIndexed(
                        when (index) {
                            0 -> seriesCardWatching.items
                            1 -> seriesCardWillWatch.items
                            2 -> seriesCardFinishedWatching.items
                            3 -> seriesCardWatched.items
                            else -> emptyList()
                        }
                    ) { _, series ->
                        SeriesItemView(
                            seriesCard = series,
                            navigateToSeriesById = navigateToSeriesById
                        )
                    }

                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        trackColor = Colors.blueColor
                    )
                }
            }
        }
    }
}

