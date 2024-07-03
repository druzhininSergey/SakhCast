package com.example.sakhcast.data.api_service

import com.example.sakhcast.model.CurrentUser
import com.example.sakhcast.model.Episode
import com.example.sakhcast.model.LastWatched
import com.example.sakhcast.model.LoginResponse
import com.example.sakhcast.model.Movie
import com.example.sakhcast.model.MovieList
import com.example.sakhcast.model.NotificationList
import com.example.sakhcast.model.Result
import com.example.sakhcast.model.Series
import com.example.sakhcast.model.SeriesList
import com.example.sakhcast.model.SeriesPlaylist
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface SakhCastApiService {
    // LOGIN
    @POST("v1/users/login")
    fun userLogin(
        @Query("login") login: String,
        @Query("password") password: String
    ): Call<LoginResponse>

    @POST("v1/users/logout")
    fun userLogout(): Call<Result>

    @GET("v2/users/current")
    fun checkLoginStatus(): Call<CurrentUser>

// HOME
    @GET("v2/users/continue")
    fun getContinueWatchMovieAndSeries(): Call<LastWatched>

// SERIES
    @GET("catalog.items")
    fun getSeriesListByCategoryName(
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40,
    ): Call<SeriesList>

    @GET("v1/catalog/items")
    fun getSeriesListByCompany(
        @Query("category") category: String = "network",
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40,
        @Query("networks") networks: String,
    ): Call<SeriesList>

    @GET("v1/catalog/items")
    fun getSeriesListByGenre(
        @Query("category") category: String = "genre",
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40,
        @Query("kind") kind: String = "watching",
        @Query("genres") genres: String,
    ): Call<SeriesList>

    @GET("serials/get")
    fun getSeriesById(
        @Query("id") id: Int
    ): Call<Series>

    @GET("v1/serials/get_episodes")
    fun getSeriesEpisodesBySeasonId(
        @Query("season_id") seasonId: Int
    ): Call<List<Episode>>

    @GET("catalog.items")
    fun getSeriesFavorites(
        @Query("category") category: String = "favorites",
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40,
        @Query("kind") kind: String,
    ): Call<SeriesList>

    @POST("v1/serials/set_fav")
    fun addSeriesInFavorites(
        @Query("serial_id") seriesId: Int,
        @Query("kind") kind: String,
        @Query("notify") notify: Int = 1,
    ): Call<String>

    @POST("v1/serials/remove_fav?serial_id=1")
    fun removeSeriesFromFavorites(
        @Query("serial_id") seriesId: Int,
    ): Call<String>

    @GET("v1/catalog/items")
    fun searchSeries(
        @Query("category") category: String = "search",
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40,
        @Query("t") textInput: String
    ): Call<SeriesList>

    @GET("v1/serial/watch/get_playlist/?season_id=6850&rg=NewStudio")
    fun getSeriesPlaylistBySeasonIdAndRgName(
        @Query("season_id") seasonId: String,
        @Query("rg") rgName: String,
    ): Call<List<SeriesPlaylist>>

    @POST("v1/serial/watch/set_pos")
    fun setSeriesEpisodePosition (
        @Query("media_id") mediaId: Int,
        @Query("t") time: Int,
    ): Call<Boolean>

// Movies
    @GET("v2/catalog/movies/items")
    fun getMoviesByCategoryName(
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40
    ): Call<MovieList>

    @GET("v2/movie/{movie}")
    fun getMovieByAlphaId(
        @Path("movie") movieAlphaId: String
    ): Call<Movie>

    @GET("v2/catalog/movies/items")
    fun getMovieRecommendationsByRefId(
        @Query("category") category: String = "recommendations",
        @Query("amount") amount: Int = 40,
        @Query("ref_movie_id") refMovieId: Int
    ): Call<MovieList>

    @GET("v2/catalog/movies/items")
    fun getMovieFavorites(
        @Query("category") category: String = "favorites",
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40,
    ): Call<MovieList>

    @GET("v2/catalog/movies/items")
    fun getMoviesListBySortField(
        @Query("sf") sortField: String,
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40,
    ): Call<MovieList>

    @GET("v2/catalog/movies/items")
    fun getMoviesListByCompanyId(
        @Query("sf") sortField: String = "Movie.sort",
        @Query("companies") companyId: String,
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40,
    ): Call<MovieList>

    @GET("v2/catalog/movies/items")
    fun getMoviesListByPersonId(
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40,
        @Query("person") personId: String,
    ): Call<MovieList>

    @GET("v2/catalog/movies/items")
    fun getMoviesListByGenreId(
        @Query("genres") genresId: String,
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40,
    ): Call<MovieList>

    @PUT("v2/movie/{movie}/fav")
    fun putMovieInFavorites(
        @Path("movie") movieAlphaId: String,
        @Query("kind") kind: String,
    ): Call<Result>

    @DELETE("v2/movie/{movie}/fav")
    fun deleteMovieFromFavorites(
        @Path("movie") movieAlphaId: String,
    ): Call<Result>

    @GET("v2/catalog/movies/items")
    fun searchMovie(
        @Query("category") category: String = "search",
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40,
        @Query("phrase") textInput: String,
    ): Call<MovieList>

    @POST("v2/movie/{movie}/pos")
    fun setMoviePosition(
        @Path("movie") movieAlphaId: String,
        @Query("t") positionSec: Int
    ): Call<Boolean>

    @GET("v2/movie/{movie}/pos")
    fun getMoviePosition(
        @Path("movie") movieAlphaId: String,
    ): Call<Int>

    @GET("v1/users/get_notifies?amount=20&from=0&subject_only=0")
    fun getNotificationsList(
        @Query("amount") amount: Int = 40,
        @Query("from") from: Int = 0,
        @Query("subject_only") subjectOnly: Int = 0,
    ): Call<NotificationList>

    @POST("v1/users/ack_all_notifies")
    fun makeAllNotificationsRead(): Call<Boolean>
}