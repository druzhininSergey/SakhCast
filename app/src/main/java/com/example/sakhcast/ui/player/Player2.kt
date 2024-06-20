package com.example.sakhcast.ui.player

import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerView
import com.example.sakhcast.data.formatMinSec
import com.example.sakhcast.data.hideSystemUi
import com.example.sakhcast.data.showSystemUi
import kotlinx.coroutines.launch

@OptIn(UnstableApi::class)
@Composable
fun Player2(
    paddingValues: PaddingValues,
    movieAlphaId: String,
    playerViewModel: PlayerViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = movieAlphaId) {
        playerViewModel.getFullMovieData(movieAlphaId)
    }
    val movieState = playerViewModel.movieWatchState.observeAsState()
    val movie = movieState.value?.movie
//    val exoPlayer = remember { ExoPlayer.Builder(context).build() }
    val exoPlayer = playerViewModel.player

    var continueTime by remember {
        mutableIntStateOf(0)
    }

    val context = LocalContext.current
    context.hideSystemUi()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = movie) {
        if (movie != null) {
            playerViewModel.startPlayer()
            continueTime = movie.userFavourite.position ?: 0
            Log.e("!!!", "continueTime = $continueTime")
            val userTime = (continueTime * 1000L).formatMinSec()

            if (continueTime != 0) scope.launch {
                snackbarHostState.showSnackbar(
                    message = "",
                    actionLabel = "Продолжить с $userTime?",
                    duration = SnackbarDuration.Long
                )
            }

        }
    }

    var shouldShowControls by remember { mutableStateOf(false) }
    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val lifecycleOwner = LocalLifecycleOwner.current

//
//    var isPlaying by remember { mutableStateOf(exoPlayer.isPlaying) }
//
//    var totalDuration by remember { mutableLongStateOf(0L) }
//
//    var currentTime by remember { mutableLongStateOf(0L) }
//
//    var bufferedPercentage by remember { mutableIntStateOf(0) }
//
//    var playbackState by remember { mutableIntStateOf(exoPlayer.playbackState) }

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        val listener =
            object : Player.Listener {
                override fun onEvents(
                    player: Player,
                    events: Player.Events
                ) {
                    super.onEvents(player, events)
//                    totalDuration = player.duration.coerceAtLeast(0L)
//                    currentTime = player.currentPosition.coerceAtLeast(0L)
//                    bufferedPercentage = player.bufferedPercentage
//                    isPlaying = player.isPlaying
//                    playbackState = player.playbackState
                }
            }
        exoPlayer.addListener(listener)
        onDispose {
            exoPlayer.release()
            lifecycleOwner.lifecycle.removeObserver(observer)
            exoPlayer.removeListener(listener)
            context.showSystemUi()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {

        AndroidView(
            modifier =
            Modifier
                .clickable { shouldShowControls = !shouldShowControls }
                .padding(paddingValues)
                .fillMaxSize(),
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = true
                    layoutParams =
                        FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    setShowSubtitleButton(true)
                    setShowNextButton(false)
                    setShowPreviousButton(false)

                    setBackgroundColor(0xFF000000.toInt())
                }
            },
            update = {
                when (lifecycle) {
                    Lifecycle.Event.ON_RESUME -> {
                        it.onPause()
                        it.player?.pause()
                    }
                    Lifecycle.Event.ON_PAUSE -> it.onResume()
                    else -> Unit
                }

            }
        )

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .padding(bottom = 90.dp, end = 16.dp)
                .align(Alignment.BottomEnd),
            snackbar = { snackbarData ->
                Button(
                    onClick = { snackbarData.performAction() },
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(width = 1.dp, color = Color.White)
                ) {
                    Text(
                        text = snackbarData.visuals.actionLabel ?: "",
                        modifier = Modifier
                            .padding(8.dp)
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .background(color = MaterialTheme.colorScheme.surface)
                    )
                }
            }
        )

    }

}


