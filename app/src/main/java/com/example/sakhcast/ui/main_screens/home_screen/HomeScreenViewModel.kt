package com.example.sakhcast.ui.main_screens.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sakhcast.data.Samples
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.MovieCard
import com.example.sakhcast.model.SeriesCard
import com.example.sakhcast.model.last_watched.LastWatched
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val sakhCastRepository: SakhCastRepository) :
    ViewModel() {

    private var _homeScreenState = MutableLiveData(HomeScreenState())
    val homeScreenState: LiveData<HomeScreenState> = _homeScreenState

    init {
        _homeScreenState.value = homeScreenState.value?.copy(
            seriesList = getSetiesListSample(),
            moviesList = getMoviesListSample(),
        )
    }

    data class HomeScreenState(
        var seriesList: List<SeriesCard> = emptyList(),
        var moviesList: List<MovieCard> = emptyList(),
        var lastWatched: LastWatched? = null,
        var lastWatchedMovieTime: String = "",
    )

    fun getLastWatched() {
        viewModelScope.launch {
            val lastWatched = sakhCastRepository.getContinueWatchMovieAndSerias()
            _homeScreenState.value = homeScreenState.value?.copy(
                lastWatched = lastWatched
            )
        }
    }

    fun convertSeconds(seconds: Int) {
        viewModelScope.launch {
            val hours = seconds / 3600
            val remainingSecondsAfterHours = seconds % 3600
            val minutes = remainingSecondsAfterHours / 60
            val finalSeconds = remainingSecondsAfterHours % 60

            val hoursPart = if (hours > 0) "${hours}час. " else ""
            val minutesPart = if (minutes > 0) "${minutes}мин. " else ""
            val secondsPart =
                if (finalSeconds > 0 || (hours == 0 && minutes == 0)) "${finalSeconds}с." else ""

            _homeScreenState.value =
                homeScreenState.value?.copy(lastWatchedMovieTime = "$hoursPart$minutesPart$secondsPart".trim())
        }
    }

    private fun getSetiesListSample() = Samples.getAllSeries()

    private fun getMoviesListSample() = Samples.getAllMovies()

}