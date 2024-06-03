package com.example.sakhcast.ui.main_screens.catalog_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatalogScreenViewModel @Inject constructor() : ViewModel() {

    private var _catalogScreenState = MutableLiveData(CatalogScreenState())
    val catalogScreenState: LiveData<CatalogScreenState> = _catalogScreenState

    data class CatalogScreenState(
        val seriesCategories: List<String> = getSeriesCategories(),
        val moviesCategories: List<String> = getMoviesCategories(),
    )

    companion object {
        fun getMoviesCategories(): List<String> = listOf(
            "Все",
            "Свежее",
            "Новинки",
            "Сейчас смотрят",
            "Мировой топ",
            "Российский топ",
            "Жанры"
        )

        fun getSeriesCategories(): List<String> = listOf(
            "Все",
            "Свежее",
            "Новинки",
            "Сейчас смотрят",
            "Мировой топ",
            "Российский топ",
            "Жанры"
        )
    }

}