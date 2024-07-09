package com.example.sakhcast.data

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.view.WindowInsets
import androidx.media3.common.C
import androidx.media3.common.TrackSelectionOverride
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.exoplayer.trackselection.MappingTrackSelector
import java.util.Locale
import java.util.concurrent.TimeUnit

fun Long.formatMinSec(): String {
    return if (this == 0L) {
        "..."
    } else {
        String.format(
            Locale.ENGLISH,
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(this),
            TimeUnit.MILLISECONDS.toMinutes(this) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(this) % TimeUnit.MINUTES.toSeconds(1)
        )
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

//fun Context.setScreenOrientation(orientation: Int) {
//    val activity = this.findActivity() ?: return
//    activity.requestedOrientation = orientation
//}

fun Context.hideSystemUi() {
    val activity = this.findActivity() ?: return
    val window = activity.window ?: return
    val decorView = window.decorView
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        decorView.windowInsetsController?.hide(WindowInsets.Type.systemBars())
    }
//    WindowCompat.setDecorFitsSystemWindows(window, false)
//    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
//    windowInsetsController.let { controller ->
//        controller.hide(WindowInsetsCompat.Type.systemBars())
//        controller.systemBarsBehavior =
//            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//    }
//    window.statusBarColor = Color.Transparent.toArgb()
//    window.navigationBarColor = activity.getColor(android.R.color.black)

}

fun Context.showSystemUi() {
    val activity = this.findActivity() ?: return
    val window = activity.window ?: return
    val decorView = window.decorView
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        decorView.windowInsetsController?.show(WindowInsets.Type.systemBars())
    }
//    WindowCompat.setDecorFitsSystemWindows(window, true)
//    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
//    windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
}

fun Context.browserIntent(dataLinc: String, secondVariantLinc: String = dataLinc) {
    val browserIntent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(dataLinc))
    val secondBrowserIntent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(secondVariantLinc))
    val repeat = this.startIntent(browserIntent)
    if (repeat) this.startIntent(secondBrowserIntent)
}

fun Context.startIntent(intent: Intent): Boolean {
    return try {
        this.startActivity(intent)
        false
    } catch (e: Exception) {
        e.printStackTrace()
        true
    }
}

fun Activity.lockOrientationLandscape() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
}

fun Activity.unlockOrientation() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
}

fun DefaultTrackSelector.generateQualityList(): ArrayList<Pair<String, TrackSelectionOverride>> {
    val trackOverrideList = ArrayList<Pair<String, TrackSelectionOverride>>()

    val renderTrack = this.currentMappedTrackInfo
    val renderCount = renderTrack?.rendererCount ?: 0
    for (rendererIndex in 0 until renderCount) {
        if (isSupportedFormat(renderTrack, rendererIndex)) {
            val trackGroupType = renderTrack?.getRendererType(rendererIndex)
            val trackGroups = renderTrack?.getTrackGroups(rendererIndex)
            val trackGroupsCount = trackGroups?.length ?: 0
            if (trackGroupType == C.TRACK_TYPE_VIDEO) {
                for (groupIndex in 0 until trackGroupsCount) {
                    val videoQualityTrackCount = trackGroups?.get(groupIndex)?.length
                    if (videoQualityTrackCount != null) {
                        for (trackIndex in 0 until videoQualityTrackCount) {
                            val isTrackSupported = renderTrack.getTrackSupport(
                                rendererIndex,
                                groupIndex,
                                trackIndex
                            ) == C.FORMAT_HANDLED
                            if (isTrackSupported) {
                                val track = trackGroups.get(groupIndex)
                                val trackName =
                                    "${track.getFormat(trackIndex).width} x ${
                                        track.getFormat(
                                            trackIndex
                                        ).height
                                    }"
                                val trackOverride = TrackSelectionOverride(track, trackIndex)
                                trackOverrideList.add(Pair(trackName, trackOverride))
                            }
                        }
                    }
                }
            }
        }
    }
    return trackOverrideList
}

fun isSupportedFormat(
    mappedTrackInfo: MappingTrackSelector.MappedTrackInfo?,
    rendererIndex: Int
): Boolean {
    val trackGroupArray = mappedTrackInfo?.getTrackGroups(rendererIndex)
    return if (trackGroupArray?.length == 0) {
        false
    } else mappedTrackInfo?.getRendererType(rendererIndex) == C.TRACK_TYPE_VIDEO
}