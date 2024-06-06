package com.example.sakhcast.ui.movie_series_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sakhcast.data.SeriesEpisodesSample
import com.example.sakhcast.data.SeriesSample
import com.example.sakhcast.model.Episode
import com.example.sakhcast.model.Series
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor() : ViewModel() {

    private var _seriesState = MutableLiveData(SeriesState())
    val seriesState: LiveData<SeriesState> = _seriesState

    init {
        getFullSeries()
    }

    data class SeriesState(
        var series: Series? = null,
        var episodeList: List<Episode> = emptyList(),
    )

    fun getFullSeries() {
        _seriesState.value = seriesState.value?.copy(
            series = SeriesSample.getFullSeries(),
            episodeList = SeriesEpisodesSample.getSeriesEpisodesList()
        )
    }

}