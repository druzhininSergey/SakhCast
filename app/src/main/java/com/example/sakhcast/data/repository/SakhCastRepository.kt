package com.example.sakhcast.data.repository

import android.util.Log
import androidx.paging.PagingSource
import com.example.sakhcast.data.api_service.SackCastApiService
import com.example.sakhcast.data.paging.SeriesPagingSource
import com.example.sakhcast.model.CurentUser
import com.example.sakhcast.model.Episode
import com.example.sakhcast.model.Genre
import com.example.sakhcast.model.LastWatched
import com.example.sakhcast.model.LoginResponse
import com.example.sakhcast.model.Movie
import com.example.sakhcast.model.MovieList
import com.example.sakhcast.model.ResultLogout
import com.example.sakhcast.model.Series
import com.example.sakhcast.model.SeriesCard
import com.example.sakhcast.model.SeriesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SakhCastRepository @Inject constructor(
    private val sackCastApiService: SackCastApiService,
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

    suspend fun getSeriesListByCategoryName(categoryName: String, page: Int): SeriesList? {
        return withContext(ioDispatcher) {
            try {
                val seriesListCall =
                    sackCastApiService.getSeriesListByCategoryName(categoryName, page)
                val responseBody = seriesListCall.execute()
                Log.i("!!!", "SeriesList from repo = ${responseBody.body()}")
                Log.i("!!!", "Сall repo = ${seriesListCall}")
                Log.i("!!!", "SeriesList from repo = ${responseBody}")
                responseBody.body()
            } catch (e: Exception) {
                Log.i("!!!", "series homescreen list = exeption")
                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getSeriesListByGenre(page: Int, genre: String): SeriesList?{
        return withContext(ioDispatcher) {
            try {
                val seriesListCall =
                    sackCastApiService.getSeriesListByGenre(page = page, genres = genre)
                val responseBody = seriesListCall.execute()
                Log.i("!!!", "SeriesList BY GENRE from repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                Log.i("!!!", "series list BY GENRE = exeption")
                Log.i("!!!", "${e.message}")
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
//                Log.i("!!!", "SeriesById = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "seriesById = exeption")
//                Log.i("!!!", "${e.message}")
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

    suspend fun getSeriesEpisodesBySeasonId(seasonId: Int): List<Episode>? {
        return withContext(ioDispatcher) {
            try {
                val episodesListCall = sackCastApiService.getSeriesEpisodesBySeasonId(seasonId)
                val responseBody = episodesListCall.execute()
//                Log.i("!!!", "Episodes from repo = ${responseBody.body()}")
//                Log.i("!!!", "SEASONID REPO = ${seasonId}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "Episodes = exeption")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getMovieRecomendationsByRefId(refMovieId: Int): MovieList? {
        return withContext(ioDispatcher) {
            try {
                val movieRecomendationsListCall =
                    sackCastApiService.getMovieRecomendationsByRefId(refMovieId = refMovieId)
                val responseBody = movieRecomendationsListCall.execute()
//                Log.i("!!!", "Recomendations list from repo = ${responseBody.body()}")
//                Log.i("!!!", "refMovieId REPO = ${refMovieId}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "Episodes = exeption")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getMovieFavories(): MovieList? {
        return withContext(ioDispatcher) {
            try {
                val movieFavoritesListCall =
                    sackCastApiService.getMovieFavories(page = 0)
                val responseBody = movieFavoritesListCall.execute()
//                Log.i("!!!", "MoviesFavorites from repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "MoviesFavorites from repo = exeption")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getSeriesFavorites(kind: String): SeriesList? {
        return withContext(ioDispatcher) {
            try {
                val seriesFavoritesListCall =
                    sackCastApiService.getSeriesFavorites(page = 0, kind = kind)
                val responseBody = seriesFavoritesListCall.execute()
//                Log.i("!!!", "SeriesFavorites from repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "SeriesFavoritesfrom repo = exeption, KIND = $kind")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    fun getSeriesByCategoryName(categoryName: String): PagingSource<Int, SeriesCard> {
        return SeriesPagingSource(categoryName, sackCastApiService)
    }

}