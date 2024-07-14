package com.example.sakhcast.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Episode(
    @SerialName("id") val id: Int,
    @SerialName("index") val index: String,
    @SerialName("name") val name: String,
    @SerialName("preview") val preview: String,
    @SerialName("preview_alt") val previewAlt: String,
    @SerialName("date") val date: String,
    @SerialName("is_viewed") val isViewed: Int,
    @SerialName("rgs") val rgs: List<RG>
)

@Immutable
@Serializable
data class RG(
    @SerialName("id") val id: Int,
    @SerialName("rg") val rg: String,
    @SerialName("runame") val runame: String,
    @SerialName("ad") val ad: Boolean,
    @SerialName("url") val url: String,
    @SerialName("url_full") val urlFull: String,
    @SerialName("img") val img: String,
    @SerialName("img_full") val imgFull: String,
    @SerialName("tracks") val tracks: List<TrackEpisode>
)

@Immutable
@Serializable
data class TrackEpisode(
    @SerialName("id") val id: Int,
    @SerialName("label") val label: String,
    @SerialName("lang") val lang: String,
    @SerialName("kind") val kind: String
)