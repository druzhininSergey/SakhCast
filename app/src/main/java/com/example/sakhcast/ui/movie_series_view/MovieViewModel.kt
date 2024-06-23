package com.example.sakhcast.ui.movie_series_view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sakhcast.data.downloader.Downloader
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.Movie
import com.example.sakhcast.model.MovieList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val sakhCastRepository: SakhCastRepository,
    private val downloader: Downloader,
) : ViewModel() {

    private var _movieState = MutableLiveData(MovieState())
    val movieState: LiveData<MovieState> = _movieState

    data class MovieState(
        var movie: Movie? = null,
        var movieRecommendationsList: MovieList? = null,
        var isFavorite: Boolean? = null,
    )

    fun downloadMovie(url: String, fileName: String) {
        downloader.downloadFile(url, fileName)
    }

    fun getFullMovieWithRecommendations(alphaId: String) {
        viewModelScope.launch {
            val movie = sakhCastRepository.getMovieByAlphaId(alphaId)
            Log.e("!!!", "INIT movie VM = $movie")
            val isFavorite = movie?.userFavourite?.isFav
            Log.e("!!!", "INIT isFavoriteState VM = $isFavorite")
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
            Log.e("!!!", "movieAlphaId onFavPushed = $movieAlphaId")

            Log.e(
                "!!!",
                "onFavoriteButtonPushed  movieState.value?.isFavorite${movieState.value?.isFavorite}, kind = $kind"
            )
            if (movieState.value?.isFavorite == false) {
                val response =
                    sakhCastRepository.putMovieInFavorites(movieAlphaId = movieAlphaId, kind = kind)
                if (response?.result == true) _movieState.value =
                    movieState.value?.copy(isFavorite = true)
                Log.e("!!!", "isFavoriteState VM = ${movieState.value?.isFavorite}")
            } else {
                val response = sakhCastRepository.deleteMovieFromFavorites(movieAlphaId)
                if (response?.result == true) _movieState.value =
                    movieState.value?.copy(isFavorite = false)
                Log.e("!!!", "isFavoriteState VM = ${movieState.value?.isFavorite}")
            }
        }
    }
}