package com.example.sakhcast.data.api_service

import com.example.sakhcast.model.CurentUser
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

interface SackCastApiService {

    @POST("v1/users/login")
    fun userLogin(
        @Query("login") login: String,
        @Query("password") password: String
    ): Call<LoginResponse>

    @POST("v1/users/logout")
    fun userLogout(): Call<ResultLogout>

    @GET("v2/users/current")
    fun checkLoginStatus(): Call<CurentUser>

    @GET("v2/users/continue")
    fun getContinueWatchMovieAndSerias(): Call<LastWatched>

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

    @GET("v2/catalog/movies/items")
    fun getMoviesByCategoryName(
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40
    ): Call<MovieList>

    @GET("serials/get")
    fun getSeriesById(
        @Query("id") id: Int
    ): Call<Series>

    @GET("v2/movie/{movie}")
    fun getMovieByAlphaId(
        @Path("movie") movieAlphaId: String
    ): Call<Movie>

    @GET("v1/serials/get_episodes")
    fun getSeriesEpisodesBySeasonId(
        @Query("season_id") seasonId: Int
    ): Call<List<Episode>>

    @GET("v2/catalog/movies/items")
    fun getMovieRecomendationsByRefId(
        @Query("category") category: String = "recommendations",
        @Query("amount") amount: Int = 40,
        @Query("ref_movie_id") refMovieId: Int
    ): Call<MovieList>

    @GET("v2/catalog/movies/items")
    fun getMovieFavories(
        @Query("category") category: String = "favorites",
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40,
    ): Call<MovieList>

    @GET("catalog.items")
    fun getSeriesFavorites(
        @Query("category") category: String = "favorites",
        @Query("page") page: Int,
        @Query("amount") amount: Int = 40,
        @Query("kind") kind: String,
    ): Call<SeriesList>
}