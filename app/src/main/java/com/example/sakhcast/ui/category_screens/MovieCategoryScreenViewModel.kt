package com.example.sakhcast.ui.category_screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sakhcast.data.Samples
import com.example.sakhcast.model.MovieCard
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieCategoryScreenViewModel @Inject constructor() : ViewModel() {

    private var _moviesCategoryScreenState = MutableLiveData(MoviesCategoryScreenState())
    val moviesCategoryScreenState: LiveData<MoviesCategoryScreenState> = _moviesCategoryScreenState

    init {
        _moviesCategoryScreenState.value = moviesCategoryScreenState.value?.copy(
//            moviesList = getMoviesList()
        )
    }

    data class MoviesCategoryScreenState(
        var moviesList: List<MovieCard> = emptyList(),
        var categoryName: String = "",
    )

//    fun getMoviesList() = Samples.getAllMovies()

    fun getSelectedCategoryName(categoryName: String) {
        _moviesCategoryScreenState.value =
            moviesCategoryScreenState.value?.copy(categoryName = categoryName)
    }
}