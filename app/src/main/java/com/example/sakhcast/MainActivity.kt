package com.example.sakhcast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sakhcast.ui.MainScreen
import com.example.sakhcast.ui.theme.SakhCastTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
