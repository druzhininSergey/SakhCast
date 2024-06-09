package com.example.sakhcast.ui.main_screens.favorites_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sakhcast.data.Samples
import com.example.sakhcast.model.MovieCard
import com.example.sakhcast.model.SeriesCard
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesScreenViewModel @Inject constructor(): ViewModel() {

    private var _favoritesScreenState = MutableLiveData(FavoritesScreenState())
    val favoritesScreenState: LiveData<FavoritesScreenState> = _favoritesScreenState

    init {
        getAllContent()
    }

    data class FavoritesScreenState(
        var seriesCardWatching: List<SeriesCard> = emptyList(),
        var seriesCardWillWatch: List<SeriesCard> = emptyList(),
        var seriesCardFinishedWatching: List<SeriesCard> = emptyList(),
        var movieCardsWillWatch: List<MovieCard> = emptyList(),
    )

    fun getAllContent(){
        _favoritesScreenState.value = favoritesScreenState.value?.copy(
//            seriesCardWatching = Samples.getAllSeries(),
//            seriesCardWillWatch = Samples.getAllSeries(),
//            seriesCardFinishedWatching = Samples.getAllSeries(),
//            movieCardsWillWatch = Samples.getAllMovies(),
        )
    }

}