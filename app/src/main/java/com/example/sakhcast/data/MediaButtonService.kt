package com.example.sakhcast.data

//import android.app.Service
//import android.content.Intent
//import android.os.IBinder
//import android.support.v4.media.session.MediaSessionCompat
//import android.support.v4.media.session.MediaSessionCompat.Callback
//
//class MediaButtonService : Service() {
//
//    private lateinit var mediaSession: MediaSessionCompat
//
//    override fun onCreate() {
//        super.onCreate()
//
//        mediaSession = MediaSessionCompat(this, "MyMediaSession").apply {
//            setCallback(object : Callback() {
//                override fun onPlay() {
//                    // Действие при нажатии кнопки воспроизведения
//                }
//
//                override fun onPause() {
//                    // Действие при нажатии кнопки паузы
//                }
//
//                override fun onSkipToNext() {
//                    // Действие при нажатии кнопки следующего трека
//                }
//
//                override fun onSkipToPrevious() {
//                    // Действие при нажатии кнопки предыдущего трека
//                }
//            })
//
//            isActive = true
//        }
//    }
//
//    override fun onBind(intent: Intent?): IBinder? {
//        return null
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mediaSession.release()
//    }
//}