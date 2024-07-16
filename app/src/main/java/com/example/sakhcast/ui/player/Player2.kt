package com.example.sakhcast.ui.player

import android.app.Activity
import android.app.PictureInPictureParams
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.util.Rational
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.annotation.OptIn
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.toAndroidRectF
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.PictureInPictureModeChangedInfo
import androidx.core.graphics.toRect
import androidx.core.util.Consumer
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.Player
import androidx.media3.common.VideoSize
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.sakhcast.R
import com.example.sakhcast.data.formatMinSec
import com.example.sakhcast.data.hideSystemUi
import com.example.sakhcast.data.lockOrientationLandscape
import com.example.sakhcast.data.showSystemUi
import com.example.sakhcast.data.unlockOrientation
import kotlinx.coroutines.launch

@OptIn(UnstableApi::class)
@Composable
fun Player2(
    hls: String,
    title: String,
    position: Int,
    movieAlphaId: String,
    navigateUp: () -> Boolean,
    playerViewModel: PlayerViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = context as? Activity
    activity?.lockOrientationLandscape()
    context.hideSystemUi()
    var isAppInForeground by remember { mutableStateOf(true) }

    var shouldEnterPipMode by rememberSaveable { mutableStateOf(false) }
    val inPipMode = rememberIsInPipMode(playerViewModel.player) { isInPipMode ->
        if (!isInPipMode) {
            shouldEnterPipMode = false
        }
    }
    val movieState = playerViewModel.movieWatchState.collectAsState()

    var continueTime by remember { mutableIntStateOf(0) }

    var lifecycle by remember { mutableStateOf(Lifecycle.Event.ON_CREATE) }
    val lifecycleOwner = LocalLifecycleOwner.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var showSnackbar by rememberSaveable { mutableStateOf(true) }
    var isControllerVisible by remember { mutableStateOf(false) }
    val resizeModes = listOf(
        AspectRatioFrameLayout.RESIZE_MODE_FIT,
        AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH,
        AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT,
        AspectRatioFrameLayout.RESIZE_MODE_FILL,
        AspectRatioFrameLayout.RESIZE_MODE_ZOOM
    )
    var currentResizeModeIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        playerViewModel.setMovieData(hls, title, position, movieAlphaId)
        playerViewModel.startPlayer()
        continueTime = movieState.value.position

        val userTime = (continueTime * 1000L).formatMinSec()
        if (continueTime != 0 && showSnackbar) scope.launch {
            snackbarHostState.showSnackbar(
                message = "Продолжить с $userTime?",
                duration = SnackbarDuration.Long,
            )
            showSnackbar = false
        }
    }

    val pipModifier = Modifier.onGloballyPositioned { layoutCoordinates ->
        val builder = PictureInPictureParams.Builder()
        if (shouldEnterPipMode && playerViewModel.player.videoSize != VideoSize.UNKNOWN) {
            val sourceRect = layoutCoordinates.boundsInWindow().toAndroidRectF().toRect()
            builder.setSourceRectHint(sourceRect)

            val width = playerViewModel.player.videoSize.width
            val height = playerViewModel.player.videoSize.height
            var ratio = Rational(width, height)

            if (ratio.toFloat() < 0.418410f) {
                ratio = Rational(41841, 100000)
            } else if (ratio.toFloat() > 2.390000f) {
                ratio = Rational(239000, 100000)
            }

            builder.setAspectRatio(ratio)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            builder.setAutoEnterEnabled(shouldEnterPipMode)
        }
        try {
            context.findActivity().setPictureInPictureParams(builder.build())
        } catch (e: Exception){
            e.stackTrace
        }
    }

    DisposableEffect(key1 = lifecycleOwner) {
        val window = (context as? Activity)?.window
        val listener = object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (isPlaying) {
                    window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                } else {
                    window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                }
                if (!isAppInForeground && !shouldEnterPipMode && isPlaying) {
                    playerViewModel.player.pause()
                }
            }
        }
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
            when(event) {
                Lifecycle.Event.ON_PAUSE -> {
                    isAppInForeground = false
                    if (!shouldEnterPipMode) {
                        playerViewModel.player.pause()
                    }
                }
                Lifecycle.Event.ON_RESUME -> {
                    isAppInForeground = true
                }
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        playerViewModel.player.addListener(listener)
        playerViewModel.player.playWhenReady = true
        onDispose {
            playerViewModel.player.removeListener(listener)
            shouldEnterPipMode = false
            window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            lifecycleOwner.lifecycle.removeObserver(observer)
            activity?.unlockOrientation()
            context.showSystemUi()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            modifier = pipModifier
                .fillMaxSize(),
            factory = {
                PlayerView(it).apply {
                    player = playerViewModel.player
                    useController = !inPipMode
                    layoutParams =
                        FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    setShowSubtitleButton(true)
                    setShowNextButton(false)
                    setShowPreviousButton(false)
                    setBackgroundColor(0xFF000000.toInt())
                    setControllerVisibilityListener(PlayerView.ControllerVisibilityListener { visibility ->
                        isControllerVisible = visibility == View.VISIBLE
                    })
                    setFullscreenButtonClickListener {
                        currentResizeModeIndex = (currentResizeModeIndex + 1) % resizeModes.size
                        resizeMode = resizeModes[currentResizeModeIndex]
                    }
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
        AnimatedVisibility(
            visible = isControllerVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            if (!inPipMode)
                TopControls(navigateUp, movieState.value.title, onPipClick = {
                    shouldEnterPipMode = true
                    context.findActivity().enterPictureInPictureMode(
                        PictureInPictureParams.Builder().build()
                    )
                })
        }

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
                    colors = ButtonColors(
                        containerColor = Color.DarkGray,
                        contentColor = Color.DarkGray,
                        disabledContentColor = Color.DarkGray,
                        disabledContainerColor = Color.DarkGray
                    ),
                    border = BorderStroke(width = 1.dp, color = Color.White)
                ) {
                    Text(
                        text = snackbarData.visuals.message,
                        modifier = Modifier
                            .padding(8.dp)
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .background(color = Color.DarkGray),
                        color = Color.White
                    )
                }
            }
        )
    }

}

@Composable
fun TopControls(navigateUp: () -> Boolean, title: String, onPipClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { navigateUp() },
            modifier = Modifier
                .size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Exit",
                tint = Color.White
            )
        }
        Text(text = title, fontWeight = FontWeight.Bold, color = Color.White)
        IconButton(onClick = onPipClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_pip),
                contentDescription = "Enter PiP mode",
                tint = Color.White
            )
        }
    }
}

@Composable
fun rememberIsInPipMode(player: Player, onPipModeChanged: (Boolean) -> Unit): Boolean {
    val activity = LocalContext.current.findActivity()
    var pipMode by remember { mutableStateOf(activity.isInPictureInPictureMode) }
    DisposableEffect(activity) {
        val observer = Consumer<PictureInPictureModeChangedInfo> { info ->
            pipMode = info.isInPictureInPictureMode
            onPipModeChanged(info.isInPictureInPictureMode)
            if (!info.isInPictureInPictureMode) {
                player.playWhenReady = false
            }
        }
        activity.addOnPictureInPictureModeChangedListener(observer)
        onDispose { activity.removeOnPictureInPictureModeChangedListener(observer) }
    }
    return pipMode
}



internal fun Context.findActivity(): ComponentActivity {
    var context = this
    while (context is ContextWrapper) {
        if (context is ComponentActivity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Picture in picture should be called in the context of an Activity")
}