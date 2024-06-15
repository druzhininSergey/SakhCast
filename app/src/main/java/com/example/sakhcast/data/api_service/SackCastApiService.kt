package com.example.sakhcast.data.api_service

import com.example.sakhcast.model.CurrentUser
import com.example.sakhcast.model.Episode
import com.example.sakhcast.model.LastWatched
import com.example.sakhcast.model.LoginResponse
import com.example.sakhcast.model.Movie
import com.example.sakhcast.model.MovieList
import com.example.sakhcast.model.ResultLogout
import com.example.sakhcast.model.Series
import com.example.sakhcast.model.SeriesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
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
    fun userLogout(): Call<ResultLogout>

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
    fun getSeriesListByGenre(
        @Query("category") category: String = "genre",
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40,
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
    fun getMoviesListByGenreId(
        @Query("genres") genresId: String,
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40,
    ): Call<MovieList>
}