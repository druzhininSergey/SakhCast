package com.example.sakhcast.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeriesList(
    @SerialName("amount")
    val amount: Int,
    @SerialName("items")
    val items: List<SeriesCard>
)

@Serializable
data class SeriesCard(
    @SerialName("cover_alt")
    val coverAlt: String,
    @SerialName("cover_colors")
    val coverColors: CoverColors?,
    @SerialName("id")
    val id: Int,
    @SerialName("imdb")
    val imdb: Boolean,
    @SerialName("imdb_rating")
    val imdbRating: Double?,
    @SerialName("kp")
    val kp: Boolean,
    @SerialName("kp_rating")
    val kpRating: Double?,
    @SerialName("name")
    val name: String,
    @SerialName("new_episodes")
    val newEpisodes: Int,
    @SerialName("progress")
    val progress: Progress?,
    @SerialName("seasons")
    val seasons: String,
    @SerialName("year")
    val year: Int
)

@Serializable
data class Progress(
    val viewed: Int,
    val amount: Int,
    val perc: Int,
    val variant: String,
)

@Serializable
data class CoverColors(
    @SerialName("background1")
    val background1: String,
    @SerialName("background2")
    val background2: String,
    @SerialName("lum")
    val lum: Double,
    @SerialName("text")
    val text: String,
    @SerialName("title")
    val title: String
)