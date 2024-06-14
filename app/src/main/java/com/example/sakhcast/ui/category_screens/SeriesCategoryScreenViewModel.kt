package com.example.sakhcast.ui.category_screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.sakhcast.data.paging.SeriesPagingSource
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.SeriesCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesCategoryScreenViewModel @Inject constructor(private val sakhCastRepository: SakhCastRepository) :
    ViewModel() {

    private val _seriesCategoryScreenState = MutableLiveData(SeriesCategoryScreenState())
    val seriesCategoryScreenState: LiveData<SeriesCategoryScreenState> = _seriesCategoryScreenState

    data class SeriesCategoryScreenState(
        var seriesPagingData: Flow<PagingData<SeriesCard>>? = null
    )

    fun initCategory(categoryName: String) {
        viewModelScope.launch {
            val pagingSource = SeriesPagingSource(sakhCastRepository, categoryName)
            val pager = Pager(
                config = PagingConfig(pageSize = 40, enablePlaceholders = false),
                pagingSourceFactory = { pagingSource }
            )
            val flow = pager.flow.cachedIn(viewModelScope)
            _seriesCategoryScreenState.value = seriesCategoryScreenState.value?.copy(seriesPagingData = flow)
        }
    }

}
