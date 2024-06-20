package com.example.sakhcast.ui.player

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val sakhCastRepository: SakhCastRepository,
    val player: Player,
) : ViewModel() {

    private var _movieWatchState = MutableLiveData(MovieWatchState())
    val movieWatchState: LiveData<MovieWatchState> = _movieWatchState

    data class MovieWatchState(
        var movie: Movie? = null,
        var positionSec: Int = 0
    )

    fun startPlayer(){
        viewModelScope.launch {
            val uri = movieWatchState.value?.movie?.sources?.defaultSource
            val title = movieWatchState.value?.movie?.ruTitle
            player.apply {
                setMediaItem(
                    MediaItem.Builder()
                        .apply {
                            setUri(uri)
                            setMediaMetadata(
                                MediaMetadata.Builder()
                                    .setDisplayTitle(title)
                                    .build()
                            )
                        }
                        .build()
                )

                prepare()
                playWhenReady = true
                Log.e("!!!", "exoplayer url = $uri, name = $title")
            }
        }
    }

    fun getFullMovieData(alphaId: String) {
        viewModelScope.launch {
            val movie = sakhCastRepository.getMovieByAlphaId(alphaId)
            val positionSec = movie?.userFavourite?.position ?: 0
            _movieWatchState.value =
                movieWatchState.value?.copy(movie = movie, positionSec = positionSec)
        }
    }

    fun setMoviePosition(positionSec: Int) {
        viewModelScope.launch {
            val movieAlphaId = movieWatchState.value?.movie?.idAlpha ?: ""
            sakhCastRepository.setMoviePosition(movieAlphaId, positionSec)
            _movieWatchState.value = movieWatchState.value?.copy(positionSec = positionSec)
        }
    }



    override fun onCleared() {
        super.onCleared()
        player.release()
    }

}