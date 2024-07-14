package com.example.sakhcast.ui.movie_series_view

import android.net.Uri
import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.sakhcast.IMDB_SEARCH_URL
import com.example.sakhcast.KINOPOISK_MOVIE_SEARCH_URL
import com.example.sakhcast.R
import com.example.sakhcast.data.browserIntent
import com.example.sakhcast.model.Cast
import com.example.sakhcast.model.Download
import com.example.sakhcast.model.Genre
import com.example.sakhcast.model.Movie
import com.example.sakhcast.model.MovieList
import com.example.sakhcast.model.Person
import com.example.sakhcast.model.ProductionCompany
import com.example.sakhcast.model.ProductionCountry
import com.example.sakhcast.ui.DividerBase
import com.example.sakhcast.ui.main_screens.home_screen.movie.MovieItemView
import java.util.Locale
import kotlin.math.min

@Preview
@Composable
fun PreviewMovieView() {
//    MovieView(PaddingValues(top = 40.dp, bottom = 40.dp), navHostController)
}

@Preview
@Composable
fun PreviewMovieInfo() {
//    MovieInfo(MovieSample.getFullMovie(), recommendationList, navHostController)
}

@Composable
fun MovieView(
    paddingValues: PaddingValues,
    alphaId: String?,
    navigateToMoviePlayer: (String, String, Int, String) -> Unit,
    navigateToMovieByAlphaId: (String) -> Unit,
    navigateToMovieCategoriesByGenresId: (String, String) -> Unit,
    navigateUp: () -> Boolean,
    movieViewModel: MovieViewModel = hiltViewModel()
) {
    val movieState = movieViewModel.movieState.observeAsState(MovieViewModel.MovieState())
    val movie = movieState.value.movie
    val recommendationList = movieState.value.movieRecommendationsList

    var alphaIdToSend by remember { mutableStateOf("") }
    var hlsToSend by remember { mutableStateOf("") }
    var titleToSend by remember { mutableStateOf("") }
    var positionToSend by remember { mutableIntStateOf(0) }
    val positionUpdated by remember {
        mutableIntStateOf(movieViewModel.position.value)
    }

    LaunchedEffect(alphaId) {
        if (alphaId != null && movie == null) movieViewModel.getFullMovieWithRecommendations(alphaId)
        if (alphaId != null && movie != null) movieViewModel.getMoviePosition(alphaId)
    }
    val isFavorite = remember {
        mutableStateOf(movieState.value.isFavorite ?: false)
    }
    LaunchedEffect(movieState.value.isFavorite) {
        isFavorite.value = movieState.value.isFavorite ?: false
        alphaIdToSend = movie?.idAlpha ?: ""
        hlsToSend = Uri.encode(movie?.sources?.defaultSource ?: "")
        titleToSend = movie?.ruTitle ?: ""
        positionToSend = movie?.userFavourite?.position ?: 0
    }

    val scrollState = rememberScrollState()
    var sizeImage by remember { mutableStateOf(IntSize.Zero) }
    val imageUrl = movie?.posterAlt + ".webp"

    val backdropColor1 =
        if (movie != null) Color(
            android.graphics.Color.parseColor(
                movie.backdropColors?.background1 ?: "#17061d"
            )
        )
        else Color.Gray
    val backdropColor2 =
        if (movie != null) Color(
            android.graphics.Color.parseColor(
                movie.backdropColors?.background2 ?: "#17061d"
            )
        )
        else Color.Blue
    val brush = Brush.verticalGradient(listOf(backdropColor1, backdropColor2))

    Box {
        Column(
            modifier = Modifier
                .padding(bottom = paddingValues.calculateBottomPadding())
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
                SubcomposeAsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha =
                                1f - ((scrollState.value.toFloat() / scrollState.maxValue) * 1.5f)
                            translationY = 0.5f * scrollState.value
                        },
                    contentScale = ContentScale.Crop,
                    loading = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(brush = brush)
                        )
                    }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    if (movie != null)
                        IconButton(
                            onClick = {
                                if (positionUpdated == 0)
                                    navigateToMoviePlayer(
                                        hlsToSend,
                                        titleToSend,
                                        positionToSend,
                                        alphaIdToSend
                                    )
                                else
                                    navigateToMoviePlayer(
                                        hlsToSend,
                                        titleToSend,
                                        positionUpdated,
                                        alphaIdToSend
                                    )
                            },
                            modifier = Modifier
                                .size(100.dp)
                                .graphicsLayer {
                                    alpha =
                                        1f - ((scrollState.value.toFloat() / scrollState.maxValue) * 2.5f)
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
                            movie?.ruTitle?.let {
                                Text(
                                    text = it,
                                    fontSize = 25.sp,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                            movie?.originTitle?.let {
                                Text(
                                    text = it,
                                    fontSize = 18.sp,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }
                }
            }
            Box(modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {
                if (movie != null && recommendationList != null) {
                    MovieInfo(
                        movie,
                        recommendationList,
                        movieViewModel::downloadMovie,
                        navigateToMovieByAlphaId,
                        navigateToMovieCategoriesByGenresId
                    )
                }
            }
        }
        movie?.ruTitle?.let {
            TopMovieBar(
                scrollState = scrollState,
                ruTitle = it,
                paddingValues = paddingValues,
                navigateUp = navigateUp,
                isFavorite = isFavorite.value,
            ) { kind: String ->
                movieViewModel.onFavoriteButtonPushed(kind)
            }
        }

    }
}


@Composable
fun MovieInfo(
    movie: Movie,
    recommendationList: MovieList,
    download: (String, String) -> Unit,
    navigateToMovieByAlphaId: (String) -> Unit,
    navigateToMovieCategoriesByGenresId: (String, String) -> Unit,
) {
    val isRatingExists = movie.imdbRating != null || movie.kpRating != null

    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
    ) {
        MovieGenres(movie.genres, navigateToMovieCategoriesByGenresId)
        if (isRatingExists) MovieRating(movie.imdbRating, movie.kpRating, movie.imdbId, movie.kpId)
        MovieCountryYearStatus(movie.productionCountries, movie.releaseDate, movie.status)
        MovieDownloads(movie.downloads, download, movie.ruTitle)
        movie.overview?.let { MovieOverview(it) }
        movie.productionCompanies?.let {
            MovieProductionCompanies(it, navigateToMovieCategoriesByGenresId)  // меняем навигацию
        }
        movie.cast?.let { MovieExpandableCastTab(it, navigateToMovieCategoriesByGenresId) }
        MovieRecommendations(
            movieRecommendations = recommendationList,
            navigateToMovieByAlphaId = navigateToMovieByAlphaId,
        )
        MovieViewsCountInfo(movie.views, movie.favorites)
    }
}

@Composable
fun MovieViewsCountInfo(views: Int, favorites: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Просмотры",
                color = Color.Gray,
                fontSize = 14.sp,
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
fun MovieRecommendations(
    movieRecommendations: MovieList,
    navigateToMovieByAlphaId: (String) -> Unit,
) {
    Text(
        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
        text = "Рекомендации",
        fontSize = 25.sp,
        color = MaterialTheme.colorScheme.onPrimary
    )
    LazyRow(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(movieRecommendations.items) { _, movie ->
            MovieItemView(
                movieCard = movie,
                navigateToMovieByAlphaId = navigateToMovieByAlphaId
            )
        }
    }
    DividerBase()
}

@Composable
fun MovieProductionCompanies(
    productionCompanies: List<ProductionCompany>,
    navigateToMovieCategoriesByGenresId: (String, String) -> Unit
) {
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
            val productionCompanyUri = Uri.encode(company.name)

            Text(
                text = company.name,
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier
                    .border(1.dp, Color.Gray, MaterialTheme.shapes.small)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .clickable {
                        navigateToMovieCategoriesByGenresId(
                            productionCompanyUri,
                            "${company.id}.company"
                        )
                    }
            )
        }
    }
    DividerBase()
}

@Composable
fun TopMovieBar(
    scrollState: ScrollState,
    ruTitle: String,
    paddingValues: PaddingValues,
    navigateUp: () -> Boolean,
    isFavorite: Boolean,
    onFavoriteButtonPushed: (String) -> Unit
) {
    var isExpandedFavorite by remember { mutableStateOf(false) }
    val alpha = if (scrollState.maxValue > 0) {
        min(1f, (scrollState.value.toFloat() / scrollState.maxValue) * 2.0f)
    } else 0f

    val primaryColor = MaterialTheme.colorScheme.primary
    val favIcon = if (isFavorite) painterResource(R.drawable.ic_star_full2)
    else painterResource(R.drawable.ic_star_empty2)
    val listFavoriteType = if (isFavorite) {
        mapOf(
            "Убрать из избранного" to "delete",
            "Буду смотреть" to "will",
            "Просмотренно" to "watched"
        )
    } else {
        mapOf(
            "Буду смотреть" to "will",
            "Просмотренно" to "watched"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp + paddingValues.calculateTopPadding())
            .background(color = primaryColor.copy(alpha = alpha))
    ) {
        Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding()))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp),
                    tint = Color.White,
                )
            }
            Text(
                text = ruTitle,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = alpha),
            )
            Box {
                Icon(
                    painter = favIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(40.dp)
                        .clickable { isExpandedFavorite = true },
                    tint = if (isFavorite) Color(0xFFFFD700) else Color.White
                )
                DropdownMenu(
                    modifier = Modifier.background(
                        color = Color.Gray.copy(alpha = 0.5f)
                    ),
                    offset = DpOffset(0.dp, 8.dp),
                    expanded = isExpandedFavorite,
                    onDismissRequest = { isExpandedFavorite = false },
                ) {
                    listFavoriteType.keys.forEach { favType ->
                        DropdownMenuItem(text = { Text(text = favType) }, onClick = {
                            listFavoriteType[favType]?.let { onFavoriteButtonPushed(it) }
                            isExpandedFavorite = false
                        })
                    }
                }
            }

        }
    }
}

@Composable
fun MovieDownloads(downloads: List<Download>, download: (String, String) -> Unit, ruTitle: String) {
    val openDialog = remember {
        mutableStateOf(false)
    }
    val selectedItemIndex = remember {
        mutableIntStateOf(0)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "Скачать",
            fontSize = 25.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(end = 16.dp)
        ) {
            downloads.forEachIndexed { index, it ->
                Text(
                    text = it.ql,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .border(1.dp, Color.Gray, MaterialTheme.shapes.small)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .clickable {
                            selectedItemIndex.intValue = index
                            openDialog.value = true
                        }
                )
            }
            if (openDialog.value) {
                val selectedDownloadFile = downloads[selectedItemIndex.intValue]
                AlertDialog(
                    onDismissRequest = { openDialog.value = false },
                    title = {
                        Text(text = "Подтвердите скачивание фильма:")
                    },
                    text = {
                        Text(text = "\"$ruTitle\". Качество: ${selectedDownloadFile.ql}")
                    },
                    confirmButton = {
                        Button(onClick = {
                            openDialog.value = false
                            download(
                                selectedDownloadFile.src,
                                "$ruTitle.${selectedDownloadFile.h}p."
                            )
                        }) {
                            Text(text = "Скачать")
                        }
                    },
                    dismissButton = {
                        Button(onClick = {
                            openDialog.value = false
                        }) {
                            Text(text = "Отменить")
                        }
                    }
                )
            }
        }
    }
    DividerBase()
}


@Composable
fun MovieCountryYearStatus(
    country: List<ProductionCountry>,
    releaseDate: String,
    movieStatus: String
) {
    val infoColumns = listOf("Страна", "Год", "Статус")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        infoColumns.forEachIndexed { index, columnName ->
            val info = when (index) {
                0 -> country.joinToString(", ") { it.name }
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
fun MovieRating(_imdbRating: Double?, _kinopoiskRating: Double?, imdbId: String?, kpId: Int?) {
    val imdbRating = String.format(Locale.US, "%.1f", _imdbRating)
    val kinopoiskRating = String.format(Locale.US, "%.1f", _kinopoiskRating)
    val context = LocalContext.current

    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        if (_imdbRating != null && _imdbRating != 0.0) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .border(
                        1.dp,
                        Color.Gray,
                        MaterialTheme.shapes.small
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .clickable { context.browserIntent("$IMDB_SEARCH_URL$imdbId/") }
            ) {
                Text(text = "IMDb:", color = Color.Gray, fontSize = 16.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = imdbRating, color = MaterialTheme.colorScheme.scrim, fontSize = 18.sp)
            }
        }
        if (_kinopoiskRating != null && _kinopoiskRating != 0.0) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .border(
                        1.dp,
                        Color.Gray,
                        MaterialTheme.shapes.small
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .clickable { context.browserIntent("$KINOPOISK_MOVIE_SEARCH_URL$kpId/") }
            ) {
                Text(text = "Кинопоиск:", color = Color.Gray, fontSize = 16.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = kinopoiskRating,
                    color = MaterialTheme.colorScheme.scrim,
                    fontSize = 18.sp
                )
            }
        }
    }
    DividerBase()
}

@Composable
fun MovieGenres(
    genres: List<Genre>,
    navigateToMovieCategoriesByGenresId: (String, String) -> Unit
) {
    LazyRow(Modifier.fillMaxWidth()) {
        itemsIndexed(genres) { _, genres ->
            TextButton(onClick = {
                navigateToMovieCategoriesByGenresId(
                    genres.name,
                    genres.id.toString()
                )
            }) {
                Text(
                    text = genres.name.uppercase(),
                    color = Color.Gray,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .border(
                            1.dp,
                            Color.Gray,
                            MaterialTheme.shapes.small
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
    DividerBase()
}

@Composable
fun MovieOverview(overview: String) {
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

@Composable
fun MovieExpandableCastTab(
    cast: Cast,
    navigateToMovieCategoriesByGenresId: (String, String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 90f else 0f,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    Row(
        modifier = Modifier
            .padding(bottom = 40.dp)
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "Состав",
            fontSize = 25.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(top = 4.dp)
                .rotate(rotationAngle)
        )
    }

    AnimatedVisibility(visible = isExpanded) {
        Column {

            val castMembers = listOfNotNull(
                cast.voiceActor?.let { "Актеры озвучки" to it },
                cast.designer?.let { "Художники" to it },
                cast.actor?.let { "Актеры" to it },
                cast.composer?.let { "Композиторы" to it },
                cast.director?.let { "Режиссеры" to it },
                cast.producer?.let { "Продюсеры" to it },
                cast.writer?.let { "Сценаристы" to it },
                cast.editor?.let { "Монтажеры" to it },
                cast.operator?.let { "Операторы" to it }
            )

            castMembers.forEach { (title, members) ->
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = title,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(members) { person ->
                        PersonItem(person, navigateToMovieCategoriesByGenresId)
                    }
                }
                DividerBase()
            }
        }
    }
}

@Composable
fun PersonItem(person: Person, navigateToMovieCategoriesByGenresId: (String, String) -> Unit) {
    val imageUrl = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        person.photoAlt + ".avif"
    } else {
        person.photoAlt + ".webp"
    }
    val backdropColor1 = Color.Gray
    val backdropColor2 = Color.Black
    val brush = Brush.verticalGradient(listOf(backdropColor1, backdropColor2))
    Column(
        modifier = Modifier
            .width(70.dp)
            .padding(vertical = 4.dp)
            .clickable {
                navigateToMovieCategoriesByGenresId(
                    person.ruName,
                    "${person.id}.person"
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(60.dp),
            contentScale = ContentScale.FillBounds,
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = brush)
                )
            }
        )
        Text(
            text = person.ruName,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
    }
}