package com.example.sakhcast.ui.movie_series_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sakhcast.data.downloader.Downloader
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.Movie
import com.example.sakhcast.model.MovieList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val sakhCastRepository: SakhCastRepository,
    private val downloader: Downloader,
) : ViewModel() {

    private var _movieState = MutableLiveData(MovieState())
    val movieState: LiveData<MovieState> = _movieState

    private val _position = MutableStateFlow(0)
    val position: StateFlow<Int> = _position

    data class MovieState(
        var movie: Movie? = null,
        var movieRecommendationsList: MovieList? = null,
        var isFavorite: Boolean? = null,
    )

    fun downloadMovie(url: String, fileName: String) {
        downloader.downloadFile(url, fileName)
    }

    fun getMoviePosition(alphaId: String) {
        if (_position.value == 0) {
            viewModelScope.launch {
                val position = sakhCastRepository.getMoviePosition(alphaId) ?: 0
                _position.value = position
            }
        }
    }

    fun getFullMovieWithRecommendations(alphaId: String) {
        viewModelScope.launch {
            val movie = sakhCastRepository.getMovieByAlphaId(alphaId)
            val isFavorite = movie?.userFavourite?.isFav
            val movieRecommendationsList =
                movie?.id?.let { sakhCastRepository.getMovieRecommendationsByRefId(it) }
            _movieState.value = movieState.value?.copy(
                movie = movie,
                movieRecommendationsList = movieRecommendationsList,
                isFavorite = isFavorite
            )
        }
    }

    fun onFavoriteButtonPushed(kind: String) {
        viewModelScope.launch {
            val movieAlphaId = movieState.value?.movie?.idAlpha ?: "0"
            if (movieState.value?.isFavorite == false) {
                val response =
                    sakhCastRepository.putMovieInFavorites(movieAlphaId = movieAlphaId, kind = kind)
                if (response?.result == true) _movieState.value =
                    movieState.value?.copy(isFavorite = true)
            } else {
                val response = sakhCastRepository.deleteMovieFromFavorites(movieAlphaId)
                if (response?.result == true) _movieState.value =
                    movieState.value?.copy(isFavorite = false)
            }
        }
    }
}