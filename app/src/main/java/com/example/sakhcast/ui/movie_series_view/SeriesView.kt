package com.example.sakhcast.ui.movie_series_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.twotone.PlayArrow
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sakhcast.R
import com.example.sakhcast.data.SeriesEpisodesSample
import com.example.sakhcast.data.SeriesSample
import com.example.sakhcast.model.Episode
import com.example.sakhcast.model.Genre
import com.example.sakhcast.model.Season
import com.example.sakhcast.model.Series
import com.example.sakhcast.model.UserFavorite
import com.example.sakhcast.ui.DividerBase
import java.util.Locale
import kotlin.math.min

@Preview
@Composable
fun PreviewSeriesView() {
    SeriesView(PaddingValues(top = 40.dp, bottom = 40.dp))
}

@Preview
@Composable
fun PreviewSeriesInfo() {
    SeriesInfo(SeriesSample.getFullSeries(), SeriesEpisodesSample.getSeriesEpisodes())
}

@Composable
fun SeriesView(paddingValues: PaddingValues) {
    val series = SeriesSample.getFullSeries()
    val seriesEpisodes = SeriesEpisodesSample.getSeriesEpisodes()
    val scrollState = rememberScrollState()
    var sizeImage by remember { mutableStateOf(IntSize.Zero) }

    Box() {
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.series_poster),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha =
                                1f - ((scrollState.value.toFloat() / scrollState.maxValue) * 1.5f)
                            translationY = 0.5f * scrollState.value
                        },
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = {}, modifier = Modifier
                        .size(100.dp)
                        .graphicsLayer {
                            alpha =
                                1f - ((scrollState.value.toFloat() / scrollState.maxValue) * 1.5f)
                            translationY = 0.5f * scrollState.value
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_play),
                            contentDescription = null,
                            modifier = Modifier.size(100.dp),
                            tint = Color.White
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                        .onGloballyPositioned {
                            sizeImage = it.size
                        }
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.primary
                                ),
                                startY = sizeImage.height.toFloat() / 2,
                                endY = sizeImage.height.toFloat()
                            )
                        ),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .padding(16.dp)
                    ) {
                        Column {
                            Text(
                                text = series.name,
                                fontSize = 25.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Text(
                                text = series.eName,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }
            Box(modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {
                SeriesInfo(series, seriesEpisodes)
            }
        }
        TopSeriesBar(scrollState, series.name, paddingValues, series.userFavorite)
    }
}


@Composable
fun SeriesInfo(series: Series, seriesEpisodes: List<Episode>) {
    val imdbRating = String.format(Locale.US, "%.1f", series.imdbRating)
    val kinopoiskRating = String.format(Locale.US, "%.1f", series.kpRating)
    val year = if (series.yearEnd == 0) "${series.year} - ..."
    else "${series.year} - ${series.yearEnd}"

    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
    ) {
        SeriesGenres(series.genres)
        SeriesRating(imdbRating = imdbRating, kinopoiskRating = kinopoiskRating)
        SeriesContryYearStatus(series.country, year, series.status)
        SeriesDownloads(series.seasons, seriesEpisodes)
        SeriesOverview(series.about)
        SeriesProductionCompanies(series.network)
        SeriesViewsCountInfo(series.views, series.favAmount)
    }
}

@Composable
fun SeriesViewsCountInfo(views: Int, favorites: Int) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Просмотры",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = views.toString(),
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Подписки",
                color = Color.Gray,
                fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = favorites.toString(),
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun SeriesProductionCompanies(productionCompanies: List<String>) {
    Text(
        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
        text = "Кинокомпании",
        fontSize = 25.sp,
        color = MaterialTheme.colorScheme.onPrimary
    )
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
    ) {
        itemsIndexed(productionCompanies) { _, company ->
            Text(
                text = company,
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier
                    .border(1.dp, Color.Gray, MaterialTheme.shapes.small)
                    .padding(4.dp)
            )
        }
    }
    DividerBase()
}

@Composable
fun TopSeriesBar(
    scrollState: ScrollState,
    ruTitle: String,
    paddingValues: PaddingValues,
    userFavorite: UserFavorite?
) {
    val alpha = min(1f, (scrollState.value.toFloat() / scrollState.maxValue) * 1.5f)
    val primaryColor = MaterialTheme.colorScheme.primary
    val isFavorite = userFavorite != null
    val favIcon = if (isFavorite) painterResource(R.drawable.ic_star_full2)
    else painterResource(R.drawable.ic_star_empty2)

    Row(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .height(60.dp)
            .background(primaryColor.copy(alpha = alpha)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowLeft,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(30.dp)
                .clickable { },
            tint = Color.White,
        )
        Text(
            text = ruTitle,
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = alpha),
        )
        Icon(
            painter = favIcon,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(40.dp),
            tint = if (isFavorite) Color(0xFFFFD700) else Color.White
        )
    }
}

@Composable
fun SeriesDownloads(seasons: List<Season>, seriesEpisodes: List<Episode>) {
    var isExpanded by remember { mutableStateOf(false) }
    var seasonSelected by remember { mutableStateOf("Сезон ${seasons[0].index}") }
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.Transparent)
        ) {
            Text(
                text = seasonSelected,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .background(
                        color = Color.Gray.copy(alpha = 0.5f),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(6.dp)
                    .clickable { isExpanded = true }
            )
            DropdownMenu(
                modifier = Modifier.background(
                    color = Color.Gray.copy(alpha = 0.5f)
                ),
                offset = DpOffset(0.dp, 8.dp),
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
            ) {
                seasons.forEach { season ->
                    DropdownMenuItem(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = { Text("Сезон ${season.index}") },
                        onClick = {
                            seasonSelected = "Сезон ${season.index}"
                            isExpanded = false
                        },
                    )
                }
            }
            LaunchedEffect(isExpanded) {
                if (isExpanded) {
                    scrollState.scrollTo(scrollState.maxValue)
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.primary
                )
                .border(width = 1.dp, color = Color.Gray, shape = MaterialTheme.shapes.small)
                .padding(5.dp)
        ) {
            Icon(imageVector = Icons.TwoTone.PlayArrow, contentDescription = null)
            Text(text = "Продолжить просмотр", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
    SeriesEpisodeView(seriesEpisodes)
    DividerBase()
}

@Composable
fun SeriesContryYearStatus(
    country: List<String>,
    releaseDate: String,
    movieStatus: String
) {
    val infoColums = listOf("Страна", "Год", "Статус")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        infoColums.forEachIndexed { index, columnName ->
            val info = when (index) {
                0 -> country.joinToString(", ")
                1 -> releaseDate.take(4)
                2 -> movieStatus
                else -> ""
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = columnName, color = Color.Gray, fontSize = 14.sp)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = info,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp
                )
            }
        }
    }
    DividerBase()
}

@Composable
fun SeriesRating(imdbRating: String, kinopoiskRating: String) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "IMDb:", color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = imdbRating, color = Color.Yellow, fontSize = 18.sp)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Кинопоиск:", color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = kinopoiskRating, color = Color.Yellow, fontSize = 18.sp)
        }
    }
    DividerBase()
}

@Composable
fun SeriesGenres(genres: List<Genre>) {
    LazyRow(Modifier.fillMaxWidth()) {
        itemsIndexed(genres) { _, genres ->
            TextButton(onClick = {}) {
                Text(
                    text = genres.name.uppercase(),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.onPrimary,
                            MaterialTheme.shapes.small
                        )
                        .padding(4.dp)
                )
            }
        }
    }
    DividerBase()
}

@Composable
fun SeriesOverview(overview: String) {
    Text(
        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
        text = "Описание",
        fontSize = 25.sp,
        color = MaterialTheme.colorScheme.onPrimary
    )
    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = overview,
        fontSize = 14.sp,
        color = Color.Gray
    )
    DividerBase()
}