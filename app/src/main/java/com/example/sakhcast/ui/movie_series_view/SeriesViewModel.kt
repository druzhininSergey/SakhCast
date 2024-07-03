package com.example.sakhcast.ui.movie_series_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.Episode
import com.example.sakhcast.model.Series
import com.example.sakhcast.model.UserContinueWatchSeries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(private val sakhCastRepository: SakhCastRepository) :
    ViewModel() {

    private var _seriesState = MutableLiveData(SeriesState())
    val seriesState: LiveData<SeriesState> = _seriesState

    data class SeriesState(
        var series: Series? = null,
        var episodeList: List<Episode> = emptyList(),
        val isFavorite: Boolean? = null,
    )

    fun getFullSeries(seriesId: Int?) {
        if (seriesId != null) {
            viewModelScope.launch {
                val series = sakhCastRepository.getSeriesById(seriesId)
                val isFavorite = series?.userFavoriteInSeries != null
                _seriesState.value = seriesState.value?.copy(
                    series = series,
                    isFavorite = isFavorite,
                )
            }
        }
    }

    fun getSeriesEpisodesBySeasonId(seasonId: Int) {
        viewModelScope.launch {
            val episodeList = sakhCastRepository.getSeriesEpisodesBySeasonId(seasonId)
            _seriesState.value = episodeList?.let { seriesState.value?.copy(episodeList = it) }
        }
    }

    fun onFavoriteButtonPushed(kind: String) {
        viewModelScope.launch {
            val seriesId = seriesState.value?.series?.id ?: 0
            if (seriesState.value?.isFavorite == false) {
                val response =
                    sakhCastRepository.addSeriesInFavorites(seriesId = seriesId, kind = kind)
                if (response == "ok") _seriesState.value =
                    seriesState.value?.copy(isFavorite = true)
            } else {
                val response = sakhCastRepository.removeSeriesFromFavorites(seriesId)
                if (response == "ok") _seriesState.value =
                    seriesState.value?.copy(isFavorite = false)
            }
        }
    }

    fun getLastMediaData() : UserContinueWatchSeries? {
        val episodeList = _seriesState.value?.episodeList
        val lastEpisodeId = _seriesState.value?.series?.userLastMediaId
        val lastSeasonId = _seriesState.value?.series?.userLastSeasonId ?: 0
        val lastMediaTime = _seriesState.value?.series?.userLastMediaTime ?: 0
        if (episodeList != null) {
            for (episode in episodeList) {
                for (rg in episode.rgs) {
                    if (rg.id == lastEpisodeId) {
                        return UserContinueWatchSeries(
                            lastMediaIndex = episode.index.toIntOrNull() ?: 1,
                            lastRgWatched = rg.rg,
                            lastSeasonId = lastSeasonId,
                            userLastTime = lastMediaTime
                        )
                    }
                }
            }
        }
        return null
    }

}