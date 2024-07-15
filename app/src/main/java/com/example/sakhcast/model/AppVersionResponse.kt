package com.example.sakhcast.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppVersionResponseItem(
    @SerialName("id") val id: Int,
    @SerialName("vendor") val vendor: String,
    @SerialName("date") val date: String,
    @SerialName("version") val version: String,
    @SerialName("filename") val filename: String,
    @SerialName("url") val url: String
)