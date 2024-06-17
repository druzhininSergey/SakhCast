package com.example.sakhcast.ui.main_screens.search_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.sakhcast.data.paging.SearchMoviesPagingSource
import com.example.sakhcast.data.paging.SearchSeriesPagingSource
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.MovieCard
import com.example.sakhcast.model.SeriesCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(private val sakhCastRepository: SakhCastRepository) :
    ViewModel() {

    private val _seriesList = MutableLiveData<Flow<PagingData<SeriesCard>>>()
    val seriesList: LiveData<Flow<PagingData<SeriesCard>>> get() = _seriesList

    private val _movieList = MutableLiveData<Flow<PagingData<MovieCard>>>()
    val movieList: LiveData<Flow<PagingData<MovieCard>>> get() = _movieList

    fun searchSeries(textInput: String) {
        viewModelScope.launch {
            val pagingSource = SearchSeriesPagingSource(sakhCastRepository, textInput)
            val pager = Pager(
                config = PagingConfig(pageSize = 40, enablePlaceholders = false),
                pagingSourceFactory = { pagingSource }
            )
            _seriesList.postValue(pager.flow.cachedIn(viewModelScope))
        }
    }

    fun searchMovies(textInput: String) {
        viewModelScope.launch {
            val pagingSource = SearchMoviesPagingSource(sakhCastRepository, textInput)
            val pager = Pager(
                config = PagingConfig(pageSize = 40, enablePlaceholders = false),
                pagingSourceFactory = { pagingSource }
            )
            _movieList.postValue(pager.flow.cachedIn(viewModelScope))
        }
    }
}