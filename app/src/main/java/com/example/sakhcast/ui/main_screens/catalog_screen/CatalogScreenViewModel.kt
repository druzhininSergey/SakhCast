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
        var seriesCategories: List<String> = getSeriesCategories(),
        var moviesCategories: List<String> = getMoviesCategories(),
    )

    companion object {
        fun getMoviesCategories(): List<String> = listOf(
            "Все жанры",
            "Все",
            "Свежее",
            "Новинки",
            "Сейчас смотрят",
            "Российский топ",
            "Мировой топ",
            "Боевик",
            "Комедия",
            "Мультфильм",
            "Стендап"
        )

        fun getSeriesCategories(): List<String> = listOf(
            "Все жанры",
            "Все",
            "Новинки",
            "Российский топ",
            "Мировой топ",
            "Сейчас смотрят",
            "По алфавиту",
            "Мини–сериал",
            "Документальный",
            "Подкаст",
            "Аниме",
            "Мультфильм",
            "Комедия",
        )
    }

}
