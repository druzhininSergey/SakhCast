package com.example.sakhcast.ui.main_screens.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.LastWatched
import com.example.sakhcast.model.MovieList
import com.example.sakhcast.model.SeriesList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val sakhCastRepository: SakhCastRepository) :
    ViewModel() {

    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState: StateFlow<HomeScreenState> = _homeScreenState.asStateFlow()

    data class HomeScreenState(
        var seriesList: SeriesList? = null,
        var moviesList: MovieList? = null,
        var lastWatched: LastWatched? = null,
        var lastWatchedMovieTime: String = "",
        var isLoading: Boolean = true,
    )

    fun refreshLastWatched() {
        viewModelScope.launch {
            val lastWatched = sakhCastRepository.getContinueWatchMovieAndSeries()
            _homeScreenState.update { currentState ->
                currentState.copy(lastWatched = lastWatched)
            }
        }
    }

    fun getAllScreenData() {
        viewModelScope.launch {
            _homeScreenState.update { currentState ->
                currentState.copy(isLoading = true) }
            try {
                val lastWatched = sakhCastRepository.getContinueWatchMovieAndSeries()
                val seriesList = sakhCastRepository.getSeriesListByCategoryName(categoryName = "all", page = 0)
                val moviesList = sakhCastRepository.getMoviesListByCategoryName(categoryName = "all", page = 0)
                lastWatched?.movie?.data?.userFavourite?.position?.let { convertSeconds(it) }

                _homeScreenState.update { currentState ->
                    currentState.copy(
                        lastWatched = lastWatched,
                        seriesList = seriesList,
                        moviesList = moviesList,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _homeScreenState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun convertSeconds(seconds: Int) {
        viewModelScope.launch {
            val hours = seconds / 3600
            val remainingSecondsAfterHours = seconds % 3600
            val minutes = remainingSecondsAfterHours / 60
            val finalSeconds = remainingSecondsAfterHours % 60

            val hoursPart = if (hours > 0) "${hours}час. " else ""
            val minutesPart = if (minutes > 0) "${minutes}мин. " else ""
            val secondsPart =
                if (finalSeconds > 0 || (hours == 0 && minutes == 0)) "${finalSeconds}с." else ""

            _homeScreenState.update { currentState: HomeScreenState ->
                currentState.copy(lastWatchedMovieTime = "$hoursPart$minutesPart$secondsPart".trim())
            }
        }
    }

}