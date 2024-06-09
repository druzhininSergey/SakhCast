package com.example.sakhcast.model.last_watched

import com.example.sakhcast.model.BackdropColors
import com.example.sakhcast.model.PosterColors
import com.example.sakhcast.model.User
import kotlinx.serialization.Serializable

@Serializable
data class LastWatched(
    val movie: MovieRecent,
    val serial: SeriesRecent
)
@Serializable
data class MovieRecent(
    val data: MovieDataRecent,
    val time: Int
)
@Serializable
data class SeriesRecent(
    val data: SerialDataRecent,
    val time: Int
)
@Serializable
data class UserFavorite(
    val id: Int,
    val kind: String,
    val notify: Int,
    val serial_id: Int,
    val user_id: Int,
    val viewed: Int,
    val voices: String
)

@Serializable
data class MovieDataRecent(
    val adult: Boolean,
    val agelimits: Int,
    val available: Boolean,
    val backdrop: String,
    val backdrop_alt: String,
    val backdrop_colors: BackdropColors,
    val cover: String,
    val cover_alt: String,
    val cover_colors: PosterColors,
    val cover_h: Int,
    val cover_lq: String,
    val cover_w: Int,
    val id: Int,
    val id_alpha: String,
    val imdb: Boolean,
    val imdb_rating: Double,
    val is_dark_backdrop: Boolean,
    val kp: Boolean,
    val kp_rating: Double,
    val link: String,
    val origin_title: String,
    val release_date: String,
    val ru_title: String,
    val runtime: Int,
    val user: User
)
@Serializable
data class SerialDataRecent(
    val agelimits: Int,
    val backdrop: String,
    val backdrop_alt: String,
    val backdrop_colors: BackdropColors,
    val country: String,
    val ename: String,
    val id: Int,
    val imdb_rating: Int,
    val imdb_url: String,
    val is_dark_backdrop: Boolean,
    val kp_id: Int,
    val kp_rating: Double,
    val name: String,
    val network: String,
    val poster: String,
    val poster_alt: String,
    val poster_colors: PosterColors,
    val runtime: String,
    val status: String,
    val tvshow: String,
    val url: String,
    val user_favorite: UserFavorite,
    val user_last_ep: String,
    val user_last_media: String,
    val user_last_media_id: Int,
    val user_last_media_time: Int,
    val user_last_season: String,
    val user_last_season_id: Int,
    val views: Int,
    val website: String,
    val year: Int,
    val year_end: Int
)