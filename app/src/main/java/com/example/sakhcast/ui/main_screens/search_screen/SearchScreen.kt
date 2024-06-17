package com.example.sakhcast.ui.main_screens.search_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.sakhcast.Dimens
import com.example.sakhcast.ui.category_screens.MovieCategoryCardItem
import com.example.sakhcast.ui.category_screens.SeriesCategoryCardItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    searchScreenViewModel: SearchScreenViewModel = hiltViewModel()
) {
    val seriesPagingItems =
        searchScreenViewModel.seriesList.observeAsState().value?.collectAsLazyPagingItems()
    val moviePagingItems =
        searchScreenViewModel.movieList.observeAsState().value?.collectAsLazyPagingItems()
    var textInput by rememberSaveable { mutableStateOf("") }
    val tabList = listOf("Сериалы", "Фильмы")
    var tabIndex by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { tabList.size }
    val lazyGridState = rememberLazyGridState()
    var searchJob: Job? = remember { null }
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(tabIndex) {
        pagerState.animateScrollToPage(tabIndex)
        if (textInput.isNotEmpty()) {
            if (tabIndex == 0) {
                searchScreenViewModel.searchSeries(textInput)
            } else {
                searchScreenViewModel.searchMovies(textInput)
            }
        }
    }
    LaunchedEffect(pagerState.currentPage) {
        tabIndex = pagerState.currentPage
    }
    LaunchedEffect(key1 = textInput) {
        searchJob?.cancel()
        searchJob = launch {
            delay(1000)
            if (textInput.isNotEmpty() && textInput.length > 2) {
                if (tabIndex == 0) {
                    searchScreenViewModel.searchSeries(textInput)
                } else {
                    searchScreenViewModel.searchMovies(textInput)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = MaterialTheme.colorScheme.primary,
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (tabIndex == 0) searchScreenViewModel.searchSeries(textInput)
                    else searchScreenViewModel.searchMovies(textInput)
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier.padding(top = 20.dp),
            shape = RoundedCornerShape(10.dp),
            value = textInput,
            onValueChange = { textInput = it },
            label = {
                Text(
                    text = "Введите название...",
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            },
            leadingIcon = { Icon(imageVector = Icons.TwoTone.Search, contentDescription = null) },
            singleLine = true,
        )
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = MaterialTheme.colorScheme.primary,
            divider = { HorizontalDivider(color = MaterialTheme.colorScheme.primary) },
            indicator = { tabPositions ->
                SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                    color = Color.White
                )
            }
        ) {
            tabList.forEachIndexed { index, text ->
                Tab(
                    selected = index == tabIndex,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = text,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false
        ) { index ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding()
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.mainPadding),
                verticalArrangement = Arrangement.spacedBy(Dimens.mainPadding),
                contentPadding = PaddingValues(Dimens.mainPadding),
                state = lazyGridState
            ) {
                if (index == 0) {
                    seriesPagingItems?.itemCount?.let {
                        items(it) { index2 ->
                            seriesPagingItems[index2]?.let { seriesCard ->
                                SeriesCategoryCardItem(seriesCard, navHostController)
                            }
                        }
                    }
                } else {
                    moviePagingItems?.itemCount?.let {
                        items(it) { index3 ->
                            moviePagingItems[index3]?.let { movieCard ->
                                MovieCategoryCardItem(movieCard, navHostController)
                            }
                        }
                    }
                }
            }
        }
    }
}