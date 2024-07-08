package com.example.sakhcast.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    @SerialName("ad") val ad: Boolean?,
    @SerialName("adult") val adult: Boolean,
    @SerialName("agelimits") val ageLimits: Int,
    @SerialName("audio_tracks") val audioTracks: List<AudioTrack>,
    @SerialName("backdrop") val backdrop: String,
    @SerialName("backdrop_alt") val backdropAlt: String,
    @SerialName("backdrop_colors") val backdropColors: BackdropColors,
    @SerialName("budget") val budget: Int?,
    @SerialName("create_date") val createDate: String,
    @SerialName("downloads") val downloads: List<Download>,
    @SerialName("favorites") val favorites: Int,
    @SerialName("genres") val genres: List<Genre>,
    @SerialName("hidden") val hidden: Boolean,
    @SerialName("id") val id: Int,
    @SerialName("id_alpha") val idAlpha: String,
    @SerialName("imdb_rating") val imdbRating: Double?,
    @SerialName("imdb_id") val imdbId: String?,
    @SerialName("is_dark_backdrop") val isDarkBackdrop: Boolean,
    @SerialName("kp_rating") val kpRating: Double?,
    @SerialName("kp_id") val kpId: Int?,
    @SerialName("languages") val languages: List<Language>,
    @SerialName("origin_lang") val originLang: String,
    @SerialName("origin_title") val originTitle: String,
    @SerialName("overview") val overview: String?,
    @SerialName("popularity") val popularity: Double,
    @SerialName("poster") val poster: String,
    @SerialName("poster_alt") val posterAlt: String,
    @SerialName("poster_colors") val posterColors: PosterColors,
    @SerialName("production_companies") val productionCompanies: List<ProductionCompany>?,
    @SerialName("production_countries") val productionCountries: List<ProductionCountry>,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("revenue") val revenue: Int?,
    @SerialName("ru_title") val ruTitle: String,
    @SerialName("runtime") val runtime: Int?,
    @SerialName("sources") val sources: Sources,
    @SerialName("status") val status: String,
    @SerialName("subtitles") val subtitles: List<Subtitle>,
    @SerialName("tagline") val tagline: String?,
    @SerialName("url") val url: String,
    @SerialName("user") val userFavourite: UserFavourite,
    @SerialName("views") val views: Int,
    @SerialName("cast") val cast: Cast?,
)

@Serializable
data class AudioTrack(
    @SerialName("id") val id: Int,
    @SerialName("iso_639_1") val iso6391: String,
    @SerialName("title") val title: String
)

@Serializable
data class BackdropColors(
    @SerialName("background1") val background1: String,
    @SerialName("background2") val background2: String,
    @SerialName("lum") val lum: Double,
    @SerialName("text") val text: String,
    @SerialName("title") val title: String,
)

@Serializable
data class Download(
    @SerialName("h") val h: Int,
    @SerialName("id") val id: Int,
    @SerialName("ql") val ql: String,
    @SerialName("size") val size: String,
    @SerialName("size_raw") val sizeRaw: Long,
    @SerialName("src") val src: String,
    @SerialName("w") val w: Int,
)

@Serializable
data class Cast(
    @SerialName("voice_actor") val voiceActor: List<Person>? = null,
    @SerialName("designer") val designer: List<Person>? = null,
    @SerialName("actor") val actor: List<Person>? = null,
    @SerialName("composer") val composer: List<Person>? = null,
    @SerialName("director") val director: List<Person>? = null,
    @SerialName("producer") val producer: List<Person>? = null,
    @SerialName("writer") val writer: List<Person>? = null,
    @SerialName("editor") val editor: List<Person>? = null,
    @SerialName("operator") val operator: List<Person>? = null,
)

@Serializable
data class Person(
    @SerialName("id") val id: Int,
    @SerialName("kp_id") val kpId: Int,
    @SerialName("ru_name") val ruName: String,
    @SerialName("en_name") val enName: String?,
    @SerialName("photo") val photo: String,
    @SerialName("photo_alt") val photoAlt: String,
    @SerialName("description") val description: String?,
    @SerialName("ru_role") val ruRole: String,
    @SerialName("en_role") val enRole: String,
)

@Serializable
data class File(
    @SerialName("h") val h: Int,
    @SerialName("id") val id: Int,
    @SerialName("ql") val ql: String,
    @SerialName("src") val src: String,
    @SerialName("w") val w: Int,
)

@Serializable
data class Genre(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("variant") val variant: String,
)

@Serializable
data class Language(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
)

@Serializable
data class Pd(
    @SerialName("files") val files: List<File>,
    @SerialName("tracks") val tracks: List<Track>,
)

@Serializable
data class PosterColors(
    @SerialName("background1") val background1: String,
    @SerialName("background2") val background2: String,
    @SerialName("lum") val lum: Double,
    @SerialName("text") val text: String,
    @SerialName("title") val title: String,
)

@Serializable
data class ProductionCompany(
    @SerialName("id") val id: Int,
    @SerialName("logo") val logo: String?,
    @SerialName("logo_alt") val logoAlt: String?,
    @SerialName("name") val name: String,
    @SerialName("origin_country") val originCountry: String,
)

@Serializable
data class ProductionCountry(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
)

@Serializable
data class Sources(
    @SerialName("default") val defaultSource: String,
    @SerialName("pd") val pd: Pd,
    @SerialName("variants") val variants: List<Variant>,
)

@Serializable
data class Subtitle(
    @SerialName("id") val id: Int,
    @SerialName("iso_639_1") val iso6391: String,
    @SerialName("title") val title: String,
)

@Serializable
data class Track(
    @SerialName("id") val id: Int,
    @SerialName("label") val label: String,
    @SerialName("language") val language: String,
    @SerialName("src") val src: String,
)

@Serializable
data class UserFavourite(
    @SerialName("fav_kind") val favKind: String?,
    @SerialName("is_fav") val isFav: Boolean?,
    @SerialName("position") val position: Int?,
)

@Serializable
data class Variant(
    @SerialName("type") val type: String,
    @SerialName("url") val url: String,
)

