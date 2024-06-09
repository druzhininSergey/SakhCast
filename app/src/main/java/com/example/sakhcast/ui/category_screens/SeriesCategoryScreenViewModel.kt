package com.example.sakhcast.ui.category_screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sakhcast.data.Samples
import com.example.sakhcast.model.SeriesCard
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeriesCategoryScreenViewModel @Inject constructor() : ViewModel() {

    private var _seriesCategoryScreenState = MutableLiveData(SeriesCategoryScreenState())
    val seriesCategoryScreenState: LiveData<SeriesCategoryScreenState> = _seriesCategoryScreenState

    init {
        _seriesCategoryScreenState.value = seriesCategoryScreenState.value?.copy(
//            seriesList = getSeriesList()
        )
    }

    data class SeriesCategoryScreenState(
        var seriesList: List<SeriesCard> = emptyList(),
        var categoryName: String = "",
    )

//    fun getSeriesList() = Samples.getAllSeries()

    fun getSelectedCategoryName(categoryName: String) {
        _seriesCategoryScreenState.value =
            seriesCategoryScreenState.value?.copy(categoryName = categoryName)
    }

}