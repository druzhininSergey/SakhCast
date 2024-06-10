package com.example.sakhcast.data.repository

import android.util.Log
import com.example.sakhcast.data.api_service.SackCastApiService
import com.example.sakhcast.model.CurentUser
import com.example.sakhcast.model.LastWatched
import com.example.sakhcast.model.LoginResponse
import com.example.sakhcast.model.Movie
import com.example.sakhcast.model.MovieList
import com.example.sakhcast.model.ResultLogout
import com.example.sakhcast.model.Series
import com.example.sakhcast.model.SeriesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SakhCastRepository @Inject constructor(
    private val sackCastApiService: SackCastApiService,
    private val json: Json
) {
    private val ioDispatcher: CoroutineContext = Dispatchers.IO

    suspend fun userLogin(loginInput: String, passwordInput: String): LoginResponse? {
        return withContext(ioDispatcher) {
            try {
                val loginCall = sackCastApiService.userLogin(loginInput, passwordInput)
                val responseBody = loginCall.execute()
//                Log.i("!!!", "Login response body: ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun userLogout(): ResultLogout? {
        return withContext(ioDispatcher) {
            try {
                val logoutCall = sackCastApiService.userLogout()
                val responseBody = logoutCall.execute()
//                Log.i("!!!", "Logout response body: ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "Logout exception = null запрос не отправлен")
                null
            }
        }
    }

    suspend fun checkLoginStatus(): CurentUser? {
        return withContext(ioDispatcher) {
            try {
                val loginStatusCall = sackCastApiService.checkLoginStatus()
                val responseBody = loginStatusCall.execute()
//                Log.i("!!!", "userCheck = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun getContinueWatchMovieAndSerias(): LastWatched? {
        return withContext(ioDispatcher) {
            try {
                val lastWatchedCall = sackCastApiService.getContinueWatchMovieAndSerias()
                val responseBody = lastWatchedCall.execute()
//                Log.i("!!!", "LastWatched = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "LastWatched = exeption")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getSeriesListPopular(categoryName: String, page: Int): SeriesList? {
        return withContext(ioDispatcher) {
            try {
                val seriesListCall =
                    sackCastApiService.getSeriesListByCategoryName(categoryName, page)
                val responseBody = seriesListCall.execute()
//                Log.i("!!!", "SeriesList from repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "series homescreen list = exeption")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getMoviesListByCategoryName(categoryName: String, page: Int): MovieList? {
        return withContext(ioDispatcher) {
            try {
                val moviesListCall = sackCastApiService.getMoviesByCategoryName(categoryName, page)
                val responseBody = moviesListCall.execute()
//                Log.i("!!!", "MoviesList from repo = ${responseBody.body()}")
//                Log.i("!!!", "MoviesList code from repo = ${responseBody.code()}")

                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "movies homescreen list = exeption")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getSeriesById(seriesId: Int): Series? {
        return withContext(ioDispatcher) {
            try {
                val seriesCall = sackCastApiService.getSeriesById(seriesId)
                val responseBody = seriesCall.execute()
                Log.i("!!!", "SeriesById = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                Log.i("!!!", "seriesById = exeption")
                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getMovieByAlphaId(movieAlphaId: String): Movie? {
        return withContext(ioDispatcher) {
            try {
                val movieCall = sackCastApiService.getMovieByAlphaId(movieAlphaId)
                val responseBody = movieCall.execute()
//                Log.i("!!!", "MovieById from repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "movieById = exeption")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

}