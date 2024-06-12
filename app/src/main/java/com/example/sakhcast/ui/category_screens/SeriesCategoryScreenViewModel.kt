package com.example.sakhcast.ui.category_screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.SeriesList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesCategoryScreenViewModel @Inject constructor(private val sakhCastRepository: SakhCastRepository) :
    ViewModel() {

    private var _seriesCategoryScreenState = MutableLiveData(SeriesCategoryScreenState())
    val seriesCategoryScreenState: LiveData<SeriesCategoryScreenState> = _seriesCategoryScreenState

    init {
        _seriesCategoryScreenState.value = seriesCategoryScreenState.value?.copy(
//            seriesList = getSeriesList()
        )
    }

    data class SeriesCategoryScreenState(
        var seriesList: SeriesList? = null,
        var categoryName: String = "",
    )

//    fun getSeriesList() = Samples.getAllSeries()

    fun getSelectedCategoryName(categoryName: String) {
        viewModelScope.launch {
            val categoryList = mapOf(
                "Все" to "all",
                "Новинки" to "new",
                "Российский топ" to "top_kp",
                "Мировой топ" to "top_imdb",
                "Сейчас смотрят" to "popular",
                "По алфавиту" to "abc",
            )
            var categoryNameUrl = ""
            val seriesList: SeriesList? =
                if (categoryName in categoryList) {
                    categoryNameUrl = categoryList[categoryName] ?: ""
                    sakhCastRepository.getSeriesListByCategoryName(
                        categoryName = categoryNameUrl,
                        page = 0
                    )
                } else {
                    categoryNameUrl = categoryName.lowercase()
                    sakhCastRepository.getSeriesListByGenre(page = 0, genre = categoryNameUrl)
                }

            _seriesCategoryScreenState.value =
                seriesCategoryScreenState.value?.copy(
                    seriesList = seriesList,
                    categoryName = categoryName,
                )
        }

    }

}