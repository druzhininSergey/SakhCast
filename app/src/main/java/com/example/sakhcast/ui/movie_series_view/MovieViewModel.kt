package com.example.sakhcast.ui.movie_series_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.Movie
import com.example.sakhcast.model.MovieList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val sakhCastRepository: SakhCastRepository) :
    ViewModel() {

    private var _movieState = MutableLiveData(MovieState())
    val movieState: LiveData<MovieState> = _movieState

    data class MovieState(
        var movie: Movie? = null,
        var movieRecomendationsList: MovieList? = null
    )

    fun getFullMovieWithRecomendations(alphaId: String) {
        viewModelScope.launch {
            val movie = sakhCastRepository.getMovieByAlphaId(alphaId)
            val movieRecomendationsList =
                movie?.id?.let { sakhCastRepository.getMovieRecommendationsByRefId(it) }
            _movieState.value = movieState.value?.copy(
                movie = movie,
                movieRecomendationsList = movieRecomendationsList
            )
        }
    }
}