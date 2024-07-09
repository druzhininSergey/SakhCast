package com.example.sakhcast.ui.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.common.TrackSelectionOverride
import androidx.media3.common.TrackSelectionParameters
import com.example.sakhcast.data.repository.SakhCastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val sakhCastRepository: SakhCastRepository,
    val player: Player,
) : ViewModel() {

    private val _movieWatchState = MutableStateFlow(MovieWatchState())
    val movieWatchState: StateFlow<MovieWatchState> = _movieWatchState

    private var _isPositionSending = MutableStateFlow(false)
    private val isPositionSending = _isPositionSending.asStateFlow()

    data class MovieWatchState(
        var hlsUri: String = "",
        var title: String = "",
        var position: Int = 0,
        var movieAlphaId: String = "",
    )

    fun startPlayer() {
        if (!isPositionSending.value) {
//            Log.i("!!!", "startPlayer")
            _isPositionSending.value = true
            viewModelScope.launch {
                val uri = movieWatchState.value.hlsUri
                val title = movieWatchState.value.title
                player.setMediaItem(
                    MediaItem.Builder()
                        .setUri(uri)
                        .setMediaMetadata(
                            MediaMetadata.Builder()
                                .setDisplayTitle(title)
                                .build()
                        )
                        .build()
                )
                player.prepare()
                delay(1000)
                player.playWhenReady = true
                setMoviePosition()
            }
        }

    }

    fun setMovieData(hlsUri: String, title: String, position: Int, movieAlphaId: String) {
        viewModelScope.launch {
            _movieWatchState.value = _movieWatchState.value.copy(
                hlsUri = hlsUri,
                title = title,
                position = position,
                movieAlphaId = movieAlphaId
            )
        }
    }

    private fun setMoviePosition() {
        viewModelScope.launch {
            while (true) {
                if (player.isPlaying) {
                    delay(5000)
                    val movieAlphaId = _movieWatchState.value.movieAlphaId
                    val currentPosition = (player.currentPosition / 1000).toInt()
                    sakhCastRepository.setMoviePosition(movieAlphaId, currentPosition)
                    _movieWatchState.value = _movieWatchState.value.copy(position = currentPosition)
                } else {
                    delay(5000)
                    continue
                }
            }
        }
    }

    fun getAvailableVideoQualities(): List<Pair<String, TrackSelectionParameters>> {
        val tracks = player.currentTracks
        val videoTracks = tracks.groups.filter { it.type == C.TRACK_TYPE_VIDEO }

        val qualities = videoTracks.flatMap { trackGroup ->
            (0 until trackGroup.length).map { index ->
                val format = trackGroup.getTrackFormat(index)
                val quality = "${format.height}p"
                val parameters = player.trackSelectionParameters
                    .buildUpon()
                    .setOverrideForType(
                        TrackSelectionOverride(trackGroup.mediaTrackGroup, index)
                    )
                    .build()
                quality to parameters
            }
        }.distinctBy { it.first }
            .sortedByDescending { it.first.removeSuffix("p").toIntOrNull() ?: 0 }

        val autoParameters = player.trackSelectionParameters.buildUpon().clearOverrides().build()
        return listOf("Авто" to autoParameters) + qualities
    }

    fun setVideoQuality(parameters: TrackSelectionParameters) {
        player.trackSelectionParameters = parameters
    }

    override fun onCleared() {
        super.onCleared()
        _isPositionSending.value = false
        player.release()
    }

}