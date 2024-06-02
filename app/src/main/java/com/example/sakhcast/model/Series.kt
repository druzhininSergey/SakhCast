package com.example.sakhcast.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Series(
    @SerialName("about") val about: String,
    @SerialName("about_plain") val aboutPlain: String,
    @SerialName("agelimits") val ageLimits: Int,
    @SerialName("backdrop") val backdrop: String,
    @SerialName("backdrop_alt") val backdropAlt: String,
    @SerialName("backdrop_colors") val backdropColors: BackdropColors,
    @SerialName("country") val country: List<String>,
    @SerialName("ename") val eName: String,
    @SerialName("fav_amount") val favAmount: Int,
    @SerialName("genres") val genres: List<Genre>,
    @SerialName("id") val id: Int,
    @SerialName("imdb_rating") val imdbRating: Double,
    @SerialName("imdb_url") val imdbUrl: String,
    @SerialName("is_dark_backdrop") val isDarkBackdrop: Boolean,
    @SerialName("kp_id") val kpId: Int,
    @SerialName("kp_rating") val kpRating: Double,
    @SerialName("name") val name: String,
    @SerialName("network") val network: List<String>,
    @SerialName("networks") val networks: List<Network>,
    @SerialName("poster") val poster: String,
    @SerialName("poster_alt") val posterAlt: String,
    @SerialName("poster_colors") val posterColors: PosterColors,
    @SerialName("runtime") val runtime: String,
    @SerialName("seasons") val seasons: List<Season>,
    @SerialName("status") val status: String,
    @SerialName("titles") val titles: List<String>,
    @SerialName("trailers") val trailers: List<Trailer>,
    @SerialName("tvshow") val tvShow: String,
    @SerialName("url") val url: String,
    @SerialName("user_favorite") val userFavorite: UserFavorite?,
    @SerialName("user_last_media") val userLastMedia: String,
    @SerialName("user_last_media_id") val userLastMediaId: Int,
    @SerialName("user_last_media_time") val userLastMediaTime: Int,
    @SerialName("user_last_season") val userLastSeason: String,
    @SerialName("user_last_season_id") val userLastSeasonId: Int,
    @SerialName("user_rating") val userRating: String?,
    @SerialName("views") val views: Int,
    @SerialName("website") val website: String,
    @SerialName("year") val year: Int,
    @SerialName("year_end") val yearEnd: Int
)

@Serializable
data class Network(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String
)

@Serializable
data class Season(
    @SerialName("id") val id: Int,
    @SerialName("index") val index: String
)

@Serializable
data class Trailer(
    @SerialName("id") val id: Int,
    @SerialName("is_deleted") val isDeleted: Int,
    @SerialName("lang") val lang: String,
    @SerialName("name") val name: String,
    @SerialName("path") val path: String,
    @SerialName("serial_id") val serialId: Int,
    @SerialName("site") val site: String,
    @SerialName("size") val size: Int,
    @SerialName("type") val type: String
)

@Serializable
data class UserFavorite(
    @SerialName("id") val id: Int,
    @SerialName("kind") val kind: String,
    @SerialName("notify") val notify: Int,
    @SerialName("voices") val voices: List<String>
)