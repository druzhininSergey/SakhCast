package com.example.sakhcast.ui.movie_series_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sakhcast.data.SeriesEpisodesSample
import com.example.sakhcast.data.SeriesSample
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.Episode
import com.example.sakhcast.model.Series
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(private val sakhCastRepository: SakhCastRepository) : ViewModel() {

    private var _seriesState = MutableLiveData(SeriesState())
    val seriesState: LiveData<SeriesState> = _seriesState

    data class SeriesState(
        var series: Series? = null,
        var episodeList: List<Episode> = emptyList(),
    )

    fun getFullSeries(seriesId: Int?) {
        if (seriesId != null)
        viewModelScope.launch {
            val series = sakhCastRepository.getSeriesById(seriesId)
            _seriesState.value = seriesState.value?.copy(
                series = series
            )
        }
    }

}