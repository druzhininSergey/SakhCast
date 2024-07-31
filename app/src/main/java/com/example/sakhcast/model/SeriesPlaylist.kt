package com.example.sakhcast.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeriesPlaylist(
    @SerialName("episode_id") val episodeId: Int,
    @SerialName("episode_index") val episodeIndex: String,
    @SerialName("episode_name") val episodeName: String,
    @SerialName("episode_playlist") val episodePlaylist: String?,
    @SerialName("episode_playlist_variants") val episodePlaylistVariants: List<EpisodePlaylistVariants>,
    @SerialName("media_id") val mediaId: Int,
    @SerialName("rg") val rg: String,
)

@Serializable
data class EpisodePlaylistVariants(
    @SerialName("type") val type: String,
    @SerialName("url") val url: String,
)