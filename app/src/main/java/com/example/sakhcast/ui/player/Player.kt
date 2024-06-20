package com.example.sakhcast.ui.player

import android.util.Log
import androidx.annotation.OptIn
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
@Composable
fun Player(
    paddingValues: PaddingValues,
    movieAlphaId: String,
    playerViewModel: PlayerViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = movieAlphaId) {
        playerViewModel.getFullMovieData(movieAlphaId)
    }
    val movieState = playerViewModel.movieWatchState.observeAsState()
    val movie = movieState.value?.movie
    var url by remember {
        mutableStateOf("")
    }

    movie?.let {
        url = it.sources.defaultSource
    }

    Log.e("!!!", "url = $url")
    var lifecycle by remember { mutableStateOf(Lifecycle.Event.ON_CREATE) }

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = true
        }
    }
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            exoPlayer.release()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(key1 = url) {
        if (url.isNotEmpty()) {
            val mediaItem = MediaItem.fromUri(url)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()
        }
    }

    AndroidView(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .focusable(),
        factory = {
            PlayerView(context).also { playerView ->
                playerView.player = exoPlayer
            }
        },
        update = {
            when (lifecycle) {
                Lifecycle.Event.ON_RESUME -> {
                    it.onPause()
                    it.player?.pause()
                }

                Lifecycle.Event.ON_STOP -> it.onResume()
                else -> Unit
            }
        }
    )



}
