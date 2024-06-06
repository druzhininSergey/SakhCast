package com.example.sakhcast.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sakhcast.data.MovieSample
import com.example.sakhcast.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor() : ViewModel() {

    private var _movieState = MutableLiveData(MovieState())
    val movieState: LiveData<MovieState> = _movieState

    init {
        getFullMovie()
    }

    data class MovieState(
        var movie: Movie? = null
    )

    fun getFullMovie() {
        _movieState.value = _movieState.value?.copy(
            movie = MovieSample.getFullMovie()
        )
    }
}