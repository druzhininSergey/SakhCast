package com.example.sakhcast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sakhcast.ui.MainScreen
import com.example.sakhcast.ui.theme.SakhCastTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        enableEdgeToEdge()
        setContent {
            SakhCastTheme {
                MainScreen()
//                Scaffold {
//                    SeriesView(series = SeriesSample.getFullSeries(), paddingValues = it)
//                }
            }
        }
    }
}
