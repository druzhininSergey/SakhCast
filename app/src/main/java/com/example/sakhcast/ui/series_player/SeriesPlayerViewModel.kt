package com.example.sakhcast.ui.series_player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.Episode
import com.example.sakhcast.model.SeriesPlaylist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesPlayerViewModel @Inject constructor(
    private val sakhCastRepository: SakhCastRepository,
    val player: Player,
) : ViewModel() {

    private val _seriesWatchState = MutableStateFlow(SeriesWatchState())
    val seriesWatchState: StateFlow<SeriesWatchState> = _seriesWatchState

    private var _isPlaylistLoaded = MutableStateFlow(false)
    val isPlaylistLoaded: StateFlow<Boolean> = _isPlaylistLoaded

    private var _isDataLoaded = MutableStateFlow(false)
    val isDataLoaded: StateFlow<Boolean> = _isDataLoaded

    data class SeriesWatchState(
        var episodeList: List<Episode>? = null,
        var playlist: List<SeriesPlaylist>? = null,
        var playlistUriList: List<String>? = emptyList(),
        var seasonId: String = "",
        var seriesTitle: String = "",
        var episodeChosenIndex: Int = 0,
        var rgChosen: String = "",
        var lastWatchedTime: Int = 0,
        var currentEpisodeId: Int = 0,
    )

    fun startPlayer() {
        viewModelScope.launch {
            _isPlaylistLoaded.collect { isLoaded ->
                if (isLoaded) {
                    val playlistUriList = seriesWatchState.value.playlistUriList
                    val mediaItems = playlistUriList?.map { url ->
                        MediaItem.fromUri(url)
                    }
                    mediaItems?.let { player.setMediaItems(it) }
                    val episodeChosenIndex = _seriesWatchState.value.episodeChosenIndex
                    setCurrentEpisodeLastTimeWatched(episodeChosenIndex)
                    if (episodeChosenIndex == 0) player.seekToDefaultPosition(0)
                    else player.seekToDefaultPosition(episodeChosenIndex - 1)
                    setEpisodePosition()
                    player.prepare()
                    delay(1000)
                    player.playWhenReady = true
                }
            }
        }
    }

    private fun setCurrentEpisodeLastTimeWatched(episodeChosenIndex: Int) {
        val episode = seriesWatchState.value.episodeList
            ?.find { it.index == episodeChosenIndex.toString() }
        val rg = episode?.rgs?.find {
            it.rg.lowercase() == seriesWatchState.value.rgChosen.lowercase()
        }

        val currentEpisodeLastTime = episode?.isViewed ?: 0
        val episodeId = rg?.id ?: 0

        _seriesWatchState.value = seriesWatchState.value.copy(
            lastWatchedTime = currentEpisodeLastTime,
            currentEpisodeId = episodeId
        )
    }

    fun onEpisodeChanged() {
        val trackIndex = player.currentMediaItemIndex
        val episode = seriesWatchState.value.episodeList
            ?.find { it.index == (trackIndex + 1).toString() }
        val currentEpisodeLastTime = episode?.isViewed ?: 0
        val playList = seriesWatchState.value.playlist
        val currentEpisodeId =
            playList?.find { it.episodeIndex.toInt() == trackIndex + 1 }?.mediaId ?: 0

        _seriesWatchState.value = seriesWatchState.value.copy(
            lastWatchedTime = currentEpisodeLastTime,
            currentEpisodeId = currentEpisodeId
        )
    }

    fun setSeriesData(
        seasonId: String,
        seriesTitle: String,
        episodeChosenIndex: Int,
        rgChosen: String
    ) {
        if (seriesWatchState.value.episodeList == null) {
            viewModelScope.launch {
                val episodeList = sakhCastRepository.getSeriesEpisodesBySeasonId(seasonId.toInt())
                _seriesWatchState.value = seriesWatchState.value.copy(
                    episodeList = episodeList,
                    seasonId = seasonId,
                    seriesTitle = seriesTitle,
                    episodeChosenIndex = episodeChosenIndex,
                    rgChosen = rgChosen
                )
                _isDataLoaded.value = true
            }
        }
    }

    fun getPlaylist(seasonId: String, rgChosen: String) {
        if (seriesWatchState.value.playlist == null) {
            viewModelScope.launch {
                val playlist =
                    sakhCastRepository.getSeriesPlaylistBySeasonIdAndRgName(seasonId, rgChosen)
                val playlistUriList = playlist?.map { it.episodePlaylist }
                _seriesWatchState.value = seriesWatchState.value.copy(
                    playlist = playlist,
                    playlistUriList = playlistUriList
                )
                _isPlaylistLoaded.value = true
            }
        }
    }

    private fun setEpisodePosition() {
        viewModelScope.launch {
            while (true) {
                if (player.isPlaying) {
                    delay(5000)
                    val currentEpisodeId = _seriesWatchState.value.currentEpisodeId
                    val currentPosition = (player.currentPosition / 1000).toInt()
                    sakhCastRepository.setSeriesEpisodePosition(currentEpisodeId, currentPosition)
                } else {
                    delay(5000)
                    continue
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }

}