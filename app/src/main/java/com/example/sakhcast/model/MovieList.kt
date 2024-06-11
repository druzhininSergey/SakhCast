package com.example.sakhcast.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class MovieList(
    @SerialName("amount") val amount: Int,
    @SerialName("items") val items: List<MovieCard>,
    @SerialName("page") val page: Int,
    @SerialName("title") val title: String
)

@Serializable
data class User(
    @SerialName("fav_kind") val favKind: String?,
    @SerialName("is_fav") val isFav: Boolean
)

@Serializable
data class MovieCard(
    @SerialName("adult") val adult: Boolean,
    @SerialName("agelimits") val ageLimits: Int,
    @SerialName("available") val available: Boolean,
    @SerialName("cover") val cover: String,
    @SerialName("cover_alt") val coverAlt: String,
    @SerialName("cover_colors") val coverColors: CoverColors,
    @SerialName("cover_h") val coverH: Int,
    @SerialName("cover_lq") val coverLq: String,
    @SerialName("cover_w") val coverW: Int,
    @SerialName("id") val id: Int,
    @SerialName("id_alpha") val idAlpha: String,
    @SerialName("imdb") val imdb: Boolean,
    @SerialName("imdb_rating") val imdbRating: Double?,
    @SerialName("kp") val kp: Boolean,
    @SerialName("kp_rating") val kpRating: Double?,
    @SerialName("link") val link: String,
    @SerialName("origin_title") val originTitle: String,
    @SerialName("progress") val progress: Boolean?,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("ru_title") val ruTitle: String,
    @SerialName("runtime") val runtime: Int,
    @SerialName("user") val user: User
)
