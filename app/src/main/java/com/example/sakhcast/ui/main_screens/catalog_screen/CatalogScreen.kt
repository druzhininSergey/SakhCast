package com.example.sakhcast.ui.main_screens.catalog_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sakhcast.data.Samples
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun CatalogScreen(paddingValues: PaddingValues, navHostController: NavHostController) {
    val tabList = listOf("Сериалы", "Фильмы")
    var tabIndex by remember {
        mutableIntStateOf(0)
    }
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState {
        tabList.size
    }
    LaunchedEffect(tabIndex) {
        pagerState.animateScrollToPage(tabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        tabIndex = pagerState.currentPage
    }
    Column(modifier = Modifier.padding(paddingValues)) {
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = MaterialTheme.colorScheme.primary,
            divider = { Divider(color = MaterialTheme.colorScheme.primary) },
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
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
        ) { index ->
            val categories = if (index == 0) Samples.getSeriesCategories()
            else Samples.getMoviesCategories()
            CatalogList(categories = categories, navHostController, tabIndex)
        }
    }
}