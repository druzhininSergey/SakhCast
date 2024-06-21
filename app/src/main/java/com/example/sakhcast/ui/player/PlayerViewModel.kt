package com.example.sakhcast.ui.player

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import com.example.sakhcast.data.repository.SakhCastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val sakhCastRepository: SakhCastRepository,
    val player: Player,
) : ViewModel() {

    private val _movieWatchState = MutableStateFlow(MovieWatchState())
    val movieWatchState: StateFlow<MovieWatchState> = _movieWatchState

    data class MovieWatchState(
        var hlsUri: String = "",
        var title: String = "",
        var position: Int = 0,
        var movieAlphaId: String = "",
    )

    fun startPlayer() {
        Log.i("!!!", "вызов startPlayer")
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
//            setMoviePosition()
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

    fun setMoviePosition() {
//        Log.i("!!!", "вызов setMoviePosition")
        viewModelScope.launch {
            while (true) {
                if (player.isPlaying){
                    delay(5000)
                    val movieAlphaId = _movieWatchState.value.movieAlphaId
                    val currentPosition = (player.currentPosition / 1000).toInt()
                    sakhCastRepository.setMoviePosition(movieAlphaId, currentPosition)
                    _movieWatchState.value = _movieWatchState.value.copy(position = currentPosition)
//                    Log.i("!!!", "отправка position")
                } else {
                    delay(5000)
                    continue
                }
            }
        }
//        Log.i("!!!", "выход из функции setMoviePosition()")

    }

    fun savePlayerState(): Bundle {
        return Bundle().apply {
            putInt("position", player.currentPosition.toInt())
            putBoolean("playWhenReady", player.playWhenReady)
        }
    }

    fun restorePlayerState(bundle: Bundle) {
        val position = bundle.getInt("position")
        val playWhenReady = bundle.getBoolean("playWhenReady")
        player.seekTo(position.toLong())
        player.playWhenReady = playWhenReady
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}