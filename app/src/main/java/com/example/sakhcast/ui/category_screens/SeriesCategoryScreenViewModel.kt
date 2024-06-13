package com.example.sakhcast.ui.category_screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.SeriesCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesCategoryScreenViewModel @Inject constructor(private val sakhCastRepository: SakhCastRepository) :
    ViewModel() {

    private var _seriesCategoryScreenState = MutableLiveData(SeriesCategoryScreenState())
    val seriesCategoryScreenState: LiveData<SeriesCategoryScreenState> = _seriesCategoryScreenState

    data class SeriesCategoryScreenState(
        var seriesPagingData: Flow<PagingData<SeriesCard>>? = null,
    )

    fun initCategory(categoryName: String) {
        if (_seriesCategoryScreenState.value?.seriesPagingData == null) {
            getSeriesByCategoryName(categoryName)
        }
    }

    fun getSeriesByCategoryName(categoryName: String) {

        viewModelScope.launch {
            val categoryList = mapOf(
                "Все" to "all",
                "Новинки" to "new",
                "Российский топ" to "top_kp",
                "Мировой топ" to "top_imdb",
                "Сейчас смотрят" to "popular",
                "По алфавиту" to "abc",
            )
            val genreList = mapOf(
                "Мини–сериалы" to "мини–сериал",
                "Документальные" to "документальный",
                "Подкасты" to "подкаст",
                "Аниме" to "аниме",
                "Мультсериалы" to "мультфильм",
                "Комедии" to "комедия"
            )
            var categoryNameUrl =
                if (categoryName in categoryList) {
                    categoryList[categoryName] ?: ""
                } else {
                    genreList[categoryName] ?: ""
                }
            val seriesPagingData = Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = true
                ), // Настройте PagingConfig
                pagingSourceFactory = {
                    sakhCastRepository.getSeriesByCategoryName(categoryNameUrl)
                }
            ).flow.cachedIn(viewModelScope) // Закешируйте данные

            _seriesCategoryScreenState.value =
                seriesCategoryScreenState.value?.copy(
                    seriesPagingData = seriesPagingData
                )
        }
    }

//    fun getSeriesListByCategoryName(page: Int, categoryName: String) {
//        viewModelScope.launch {
//            val categoryList = mapOf(
//                "Все" to "all",
//                "Новинки" to "new",
//                "Российский топ" to "top_kp",
//                "Мировой топ" to "top_imdb",
//                "Сейчас смотрят" to "popular",
//                "По алфавиту" to "abc",
//            )
//            val genreList = mapOf(
//                "Мини–сериалы" to "мини–сериал",
//                "Документальные" to "документальный",
//                "Подкасты" to "подкаст",
//                "Аниме" to "аниме",
//                "Мультсериалы" to "мультфильм",
//                "Комедии" to "комедия"
//            )
//            var categoryNameUrl = ""
//            val seriesList: SeriesList? =
//                if (categoryName in categoryList) {
//                    categoryNameUrl = categoryList[categoryName] ?: ""
//                    sakhCastRepository.getSeriesListByCategoryName(
//                        categoryName = categoryNameUrl,
//                        page = 0
//                    )
//                } else {
//                    categoryNameUrl = genreList[categoryName] ?: ""
//                    sakhCastRepository.getSeriesListByGenre(page = page, genre = categoryNameUrl)
//                }
//
//            _seriesCategoryScreenState.value =
//                seriesCategoryScreenState.value?.copy(
//                    seriesList = seriesList,
//                )
//        }
//
//    }

}