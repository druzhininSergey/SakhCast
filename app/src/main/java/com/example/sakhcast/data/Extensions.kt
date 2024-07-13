package com.example.sakhcast.data

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
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

fun Int.formatMinSec(): String {
    return if (this == 0) {
        "..."
    } else {
        String.format(
            Locale.ENGLISH,
            "%02d:%02d:%02d",
            TimeUnit.SECONDS.toHours(this.toLong()),
            TimeUnit.SECONDS.toMinutes(this.toLong()) % TimeUnit.HOURS.toMinutes(1),
            this % 60
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            decorView.windowInsetsController?.let { controller ->
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                controller.hide(WindowInsets.Type.systemBars())
            }
        }
    } else {
        @Suppress("DEPRECATION")
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
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
    } else {
        @Suppress("DEPRECATION")
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
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