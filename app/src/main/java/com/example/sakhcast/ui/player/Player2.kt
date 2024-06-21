package com.example.sakhcast.ui.player

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerView
import com.example.sakhcast.data.formatMinSec
import com.example.sakhcast.data.hideSystemUi
import com.example.sakhcast.data.showSystemUi
import kotlinx.coroutines.launch

@OptIn(UnstableApi::class)
@Composable
fun Player2(
    hls: String,
    title: String,
    position: Int,
    movieAlphaId: String,
    playerViewModel: PlayerViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) { playerViewModel.setMovieData(hls, title, position, movieAlphaId) }
    val movieState = playerViewModel.movieWatchState.collectAsState()

    var continueTime by remember { mutableIntStateOf(0) }

    val context = LocalContext.current
    context.hideSystemUi()

    var lifecycle by remember { mutableStateOf(Lifecycle.Event.ON_CREATE) }
    val lifecycleOwner = LocalLifecycleOwner.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var savedPlayerState by rememberSaveable { mutableStateOf<Bundle?>(null) }
    var showSnackbar by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        playerViewModel.startPlayer()
        continueTime = movieState.value.position

        val userTime = (continueTime * 1000L).formatMinSec()
        if (continueTime != 0 && showSnackbar) scope.launch {
            Log.i("!!!", "showSnackbar")
            snackbarHostState.showSnackbar(
                message = "Продолжить с $userTime?",
                duration = SnackbarDuration.Long,
            )
            showSnackbar = false
        }
    }

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        playerViewModel.setMoviePosition()
        playerViewModel.player.playWhenReady = true
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            savedPlayerState = playerViewModel.savePlayerState()
            context.showSystemUi()
        }
    }
    LaunchedEffect(savedPlayerState) {
        savedPlayerState?.let { playerViewModel.restorePlayerState(it) }
    }
    Box(modifier = Modifier.fillMaxSize()) {

        AndroidView(
            modifier =
            Modifier
                .padding()
                .fillMaxSize(),
            factory = {
                PlayerView(context).apply {
                    player = playerViewModel.player
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
                    onClick = {
                        snackbarData.performAction()
                        playerViewModel.player.seekTo(position * 1000L)
                    },
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(width = 1.dp, color = Color.White)
                ) {
                    Text(
                        text = snackbarData.visuals.message,
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