package com.example.sakhcast.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("result") val result: Boolean
)

data class UserContinueWatchSeries(
    val lastSeasonId: Int,
    val userLastTime: Int,
    val lastMediaIndex: Int,
    val lastRgWatched: String,
)
