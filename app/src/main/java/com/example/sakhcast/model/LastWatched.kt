package com.example.sakhcast.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LastWatched(
    @SerialName("movie") val movie: MovieRecent,
    @SerialName("serial") val serial: SeriesRecent
)

@Serializable
data class MovieRecent(
    @SerialName("data") val data: MovieDataRecent,
    @SerialName("time") val time: Int
)

@Serializable
data class SeriesRecent(
    @SerialName("data") val data: SerialDataRecent,
    @SerialName("time") val time: Int
)

@Serializable
data class UserFavorite(
    @SerialName("id") val id: Int,
    @SerialName("kind") val kind: String,
    @SerialName("notify") val notify: Int,
    @SerialName("serial_id") val serialId: Int,
    @SerialName("user_id") val userId: Int,
    @SerialName("viewed") val viewed: Int,
    @SerialName("voices") val voices: String
)

@Serializable
data class MovieDataRecent(
    @SerialName("adult") val adult: Boolean,
    @SerialName("agelimits") val ageLimits: Int,
    @SerialName("available") val available: Boolean,
    @SerialName("backdrop") val backdrop: String,
    @SerialName("backdrop_alt") val backdropAlt: String,
    @SerialName("backdrop_colors") val backdropColors: BackdropColors,
    @SerialName("cover") val cover: String,
    @SerialName("cover_alt") val coverAlt: String,
    @SerialName("cover_colors") val coverColors: PosterColors?,
    @SerialName("cover_h") val coverH: Int,
    @SerialName("cover_lq") val coverLq: String,
    @SerialName("cover_w") val coverW: Int,
    @SerialName("id") val id: Int,
    @SerialName("id_alpha") val idAlpha: String,
    @SerialName("imdb") val imdb: Boolean,
    @SerialName("imdb_rating") val imdbRating: Double?,
    @SerialName("is_dark_backdrop") val isDarkBackdrop: Boolean,
    @SerialName("kp") val kp: Boolean,
    @SerialName("kp_rating") val kpRating: Double?,
    @SerialName("link") val link: String,
    @SerialName("origin_title") val originTitle: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("ru_title") val ruTitle: String,
    @SerialName("runtime") val runtime: Int,
    @SerialName("user") val userFavourite: UserFavourite?
)

@Serializable
data class SerialDataRecent(
    @SerialName("agelimits") val ageLimits: Int,
    @SerialName("backdrop") val backdrop: String,
    @SerialName("backdrop_alt") val backdropAlt: String,
    @SerialName("backdrop_colors") val backdropColors: BackdropColors,
    @SerialName("country") val country: String,
    @SerialName("ename") val ename: String,
    @SerialName("id") val id: Int,
    @SerialName("imdb_rating") val imdbRating: Double?,
    @SerialName("imdb_url") val imdbUrl: String,
    @SerialName("is_dark_backdrop") val isDarkBackdrop: Boolean,
    @SerialName("kp_id") val kpId: Int,
    @SerialName("kp_rating") val kpRating: Double?,
    @SerialName("name") val name: String,
    @SerialName("network") val network: String,
    @SerialName("poster") val poster: String,
    @SerialName("poster_alt") val posterAlt: String,
    @SerialName("poster_colors") val posterColors: PosterColors,
    @SerialName("runtime") val runtime: String,
    @SerialName("status") val status: String,
    @SerialName("tvshow") val tvshow: String,
    @SerialName("url") val url: String,
    @SerialName("user_favorite") val userFavorite: UserFavorite?,
    @SerialName("user_last_ep") val userLastEp: String,
    @SerialName("user_last_media") val userLastMedia: String,
    @SerialName("user_last_media_id") val userLastMediaId: Int,
    @SerialName("user_last_media_time") val userLastMediaTime: Int,
    @SerialName("user_last_season") val userLastSeason: String,
    @SerialName("user_last_season_id") val userLastSeasonId: Int,
    @SerialName("views") val views: Int,
    @SerialName("website") val website: String,
    @SerialName("year") val year: Int,
    @SerialName("year_end") val yearEnd: Int
)