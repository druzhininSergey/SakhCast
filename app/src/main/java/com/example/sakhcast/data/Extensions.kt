package com.example.sakhcast.data

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
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

fun Context.setScreenOrientation(orientation: Int) {
    val activity = this.findActivity() ?: return
    activity.requestedOrientation = orientation
}

fun Context.hideSystemUi() {
    val activity = this.findActivity() ?: return
    val window = activity.window ?: return
    WindowCompat.setDecorFitsSystemWindows(window, false)
    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
    windowInsetsController.let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun Context.showSystemUi() {
    val activity = this.findActivity() ?: return
    val window = activity.window ?: return
    WindowCompat.setDecorFitsSystemWindows(window, true)
    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
    windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
}

fun Context.browserIntent(dataLinc: String, secondVariantLinc: String = dataLinc) {
    val browserIntent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(dataLinc))
    val secondBrowserIntent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(secondVariantLinc))
    val repeat = this.startIntent(browserIntent)
    if (repeat) this.startIntent(secondBrowserIntent)
}

fun Context.startIntent(intent: Intent): Boolean{
    return  try {
        this.startActivity(intent)
        false
    } catch (e: Exception) {
        e.printStackTrace()
        true
    }
}