package com.example.sakhcast.model

data class SeriesCard(
    val id: Int,
    val linkUrl: String? = null,
    val name: String,
    val imdb: Boolean? = null,
    val kinopoisk: Boolean? = null,
    val imdbRating: Double,
    val kinopoiskRating: Double,
    val coverImageAltUrl: String? = null, // Добавлять в конце ссылки .webp для карточек(мелкие), .avif для больших изображений
    val releaseYear: Int,
    val totalSeasonsAndSeries: String,
    val newEpisodes: Boolean? = null,
    val progress: Boolean? = null,
    val available: Boolean? = null,
)

data class MovieCard(
    val id: Int,
    val linkUrl: String? = null,
    val ruTitle: String,
    val origin_title: String? = null,
    val imdb: Boolean? = null,
    val kinopoisk: Boolean? = null,
    val imdbRating: Double,
    val kinopoiskRating: Double,
    val coverImageAltUrl: String? = null, // Добавлять в конце ссылки .webp для карточек(мелкие), .avif для больших изображений
    val releaseDate: String,
    val duration: String,
    val progress: Boolean? = null,
    val available: Boolean? = null,
)

data class Notification(
    val id: Int? = null,
    val date: String? = null,
    val userId: Int? = null,
    val subject: String? = null,
    val text: String,
    val isRead: Boolean = false,
)

data class UserData(
    val userToken: String? = null,
    val userLogin: String,
    val userPassword: String,
)
