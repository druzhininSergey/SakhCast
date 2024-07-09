package com.example.sakhcast.ui.player

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.Player
import androidx.media3.common.TrackSelectionParameters
import androidx.media3.common.Tracks
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerView
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

    LaunchedEffect(Unit) { playerViewModel.setMovieData(hls, title, position, movieAlphaId) }
    val movieState = playerViewModel.movieWatchState.collectAsState()

    var continueTime by remember { mutableIntStateOf(0) }
    var lifecycle by remember { mutableStateOf(Lifecycle.Event.ON_CREATE) }
    val lifecycleOwner = LocalLifecycleOwner.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var showSnackbar by rememberSaveable { mutableStateOf(true) }
    var isControllerVisible by remember { mutableStateOf(false) }

    var availableQualities by remember {
        mutableStateOf<List<Pair<String, TrackSelectionParameters>>>(
            emptyList()
        )
    }
    var currentQuality by remember { mutableStateOf("Авто") }

    LaunchedEffect(Unit) {
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

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        val window = (context as? Activity)?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        lifecycleOwner.lifecycle.addObserver(observer)

        val playerListener = object : Player.Listener {
            override fun onTracksChanged(tracks: Tracks) {
                availableQualities = playerViewModel.getAvailableVideoQualities()
                if (availableQualities.none { it.first == currentQuality }) {
                    currentQuality = "Авто"
                }
            }
        }
        playerViewModel.player.addListener(playerListener)

        playerViewModel.player.playWhenReady = true
        onDispose {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            lifecycleOwner.lifecycle.removeObserver(observer)
            playerViewModel.player.removeListener(playerListener)
            activity?.unlockOrientation()
            context.showSystemUi()
        }
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
                    setControllerVisibilityListener(PlayerView.ControllerVisibilityListener { visibility ->
                        isControllerVisible = visibility == View.VISIBLE
                    })
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
            TopControls(
                navigateUp = navigateUp,
                title = movieState.value.title,
                qualities = availableQualities,
                currentQuality = currentQuality,
                onQualitySelected = { parameters ->
                    playerViewModel.setVideoQuality(parameters)
                    currentQuality =
                        availableQualities.find { it.second == parameters }?.first ?: "Авто"
                }
            )
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
fun TopControls(
    navigateUp: () -> Boolean,
    title: String,
    qualities: List<Pair<String, TrackSelectionParameters>>,
    currentQuality: String,
    onQualitySelected: (TrackSelectionParameters) -> Unit
) {
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
                tint = Color.Gray
            )
        }
        Text(text = title, fontWeight = FontWeight.Bold, color = Color.White)
        QualitySelector(
            qualities = qualities,
            currentQuality = currentQuality,
            onQualitySelected = onQualitySelected
        )
    }
}

@Composable
fun QualitySelector(
    qualities: List<Pair<String, TrackSelectionParameters>>,
    currentQuality: String,
    onQualitySelected: (TrackSelectionParameters) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Quality"
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            qualities.forEach { (quality, parameters) ->
                DropdownMenuItem(
                    onClick = {
                        onQualitySelected(parameters)
                        expanded = false
                    },
                    text = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(quality)
                            if (quality == currentQuality) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = Color.Green
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}