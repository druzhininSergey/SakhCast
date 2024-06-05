package com.example.sakhcast.ui.main_screens.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sakhcast.data.Samples
import com.example.sakhcast.model.MovieCard
import com.example.sakhcast.model.SeriesCard
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    private var _homeScreenState = MutableLiveData(HomeScreenState())
    val homeScreenState: LiveData<HomeScreenState> = _homeScreenState

    init {
        _homeScreenState.value = homeScreenState.value?.copy(
            seriesList = getSetiesListSample(),
            moviesList = getMoviesListSample(),
            lastSeriesWatched = getOneSeries(),
            lastMovieWatched = getOneMovie(),
        )
    }

    data class HomeScreenState(
        var seriesList: List<SeriesCard> = emptyList(),
        var moviesList: List<MovieCard> = emptyList(),
        var lastSeriesWatched: SeriesCard? = null,
        var lastMovieWatched: MovieCard? = null,
    )

    private fun getSetiesListSample() = Samples.getAllSeries()

    private fun getMoviesListSample() = Samples.getAllMovies()

    private fun getOneMovie() = Samples.getOneMovie()

    private fun getOneSeries() = Samples.getOneSeries()

}