package com.example.sakhcast.ui.main_screens.favorites_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sakhcast.Dimens
import com.example.sakhcast.model.SeriesCard
import com.example.sakhcast.ui.main_screens.home_screen.series.SeriesItemView

@Composable
fun SeriesPage(
    seriesCardWatching: List<SeriesCard>,
    seriesCardWillWatch: List<SeriesCard>,
    seriesCardFinishedWatching: List<SeriesCard>,
    navHostController: NavHostController
) {
    val categoryNames = listOf("Cмотрю", "Буду смотреть", "Досмотрел")
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        categoryNames.forEachIndexed { index, category ->
            Row(
                modifier = Modifier.padding(
                    horizontal = Dimens.mainPadding,
                    vertical = Dimens.mainPadding
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = category,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Icon(
                    modifier = Modifier.padding(top = 4.dp, start = 5.dp),
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null
                )
            }
            LazyRow(
                contentPadding = PaddingValues(horizontal = Dimens.mainPadding),
                horizontalArrangement = Arrangement.spacedBy(Dimens.mainPadding)
            ) {
                itemsIndexed(
                    when (index) {
                        0 -> seriesCardWatching
                        1 -> seriesCardWillWatch
                        2 -> seriesCardFinishedWatching
                        else -> emptyList()
                    }
                ) { _, series ->
                    SeriesItemView(seriesCard = series, navHostController = navHostController)
                }
            }
        }
    }
}