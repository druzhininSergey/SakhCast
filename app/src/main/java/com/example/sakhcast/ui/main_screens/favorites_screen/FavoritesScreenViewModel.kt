package com.example.sakhcast.ui.main_screens.favorites_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.MovieList
import com.example.sakhcast.model.SeriesList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesScreenViewModel @Inject constructor(private val sakhCastRepository: SakhCastRepository) :
    ViewModel() {

    private var _favoritesScreenState = MutableLiveData(FavoritesScreenState())
    val favoritesScreenState: LiveData<FavoritesScreenState> = _favoritesScreenState

    init {
        getAllContent()
    }

    data class FavoritesScreenState(
        var seriesCardWatching: SeriesList? = null,
        var seriesCardWillWatch: SeriesList? = null,
        var seriesCardFinishedWatching: SeriesList? = null,
        var seriesCardWatched: SeriesList? = null,
        var movieCardsWillWatch: MovieList? = null,
        var movieCardsWatched: MovieList? = null,
    )

    private fun getAllContent() {
        viewModelScope.launch {
            val seriesCardWatching = sakhCastRepository.getSeriesFavorites("watching")
            val seriesCardWillWatch = sakhCastRepository.getSeriesFavorites("will")
            val seriesCardFinishedWatching = sakhCastRepository.getSeriesFavorites("stopped")
            val seriesCardWatched = sakhCastRepository.getSeriesFavorites("watched")
            val movieCardsWillWatch = sakhCastRepository.getMovieFavorites(kind = "will")
            val movieCardsWatched = sakhCastRepository.getMovieFavorites(kind = "watched")

            _favoritesScreenState.value = favoritesScreenState.value?.copy(
                seriesCardWatching = seriesCardWatching,
                seriesCardWillWatch = seriesCardWillWatch,
                seriesCardFinishedWatching = seriesCardFinishedWatching,
                seriesCardWatched = seriesCardWatched,
                movieCardsWillWatch = movieCardsWillWatch,
                movieCardsWatched = movieCardsWatched,
            )
        }

    }

}