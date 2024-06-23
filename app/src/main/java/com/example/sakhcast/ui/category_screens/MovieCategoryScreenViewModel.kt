package com.example.sakhcast.ui.category_screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.sakhcast.data.paging.MoviesPagingSource
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.MovieCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieCategoryScreenViewModel
@Inject constructor(private val sakhCastRepository: SakhCastRepository) : ViewModel() {

    private var _moviesCategoryScreenState = MutableLiveData(MoviesCategoryScreenState())
    val moviesCategoryScreenState: LiveData<MoviesCategoryScreenState> = _moviesCategoryScreenState

    data class MoviesCategoryScreenState(
        var moviesPagingData: Flow<PagingData<MovieCard>>? = null,
        var runtimeString: String? = null,
    )

    fun initCategory(categoryName: String?) {
        viewModelScope.launch {
            if (categoryName != null) {
                val pagingSource = MoviesPagingSource(sakhCastRepository, categoryName)
                val pager = Pager(
                    config = PagingConfig(pageSize = 40, enablePlaceholders = false),
                    pagingSourceFactory = { pagingSource }
                )
                val flow = pager.flow.cachedIn(viewModelScope)
                _moviesCategoryScreenState.value =
                    moviesCategoryScreenState.value?.copy(moviesPagingData = flow)
            }
        }
    }

}