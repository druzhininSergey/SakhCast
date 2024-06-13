package com.example.sakhcast.data.repository

import android.util.Log
import com.example.sakhcast.data.api_service.SakhCastApiService
import com.example.sakhcast.model.CurentUser
import com.example.sakhcast.model.Episode
import com.example.sakhcast.model.LastWatched
import com.example.sakhcast.model.LoginResponse
import com.example.sakhcast.model.Movie
import com.example.sakhcast.model.MovieList
import com.example.sakhcast.model.ResultLogout
import com.example.sakhcast.model.Series
import com.example.sakhcast.model.SeriesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SakhCastRepository @Inject constructor(
    private val sakhCastApiService: SakhCastApiService,
) {
    private val ioDispatcher: CoroutineContext = Dispatchers.IO

    suspend fun userLogin(loginInput: String, passwordInput: String): LoginResponse? {
        return withContext(ioDispatcher) {
            try {
                val loginCall = sakhCastApiService.userLogin(loginInput, passwordInput)
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
                val logoutCall = sakhCastApiService.userLogout()
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
                val loginStatusCall = sakhCastApiService.checkLoginStatus()
                val responseBody = loginStatusCall.execute()
//                Log.i("!!!", "userCheck = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun getContinueWatchMovieAndSeries(): LastWatched? {
        return withContext(ioDispatcher) {
            try {
                val lastWatchedCall = sakhCastApiService.getContinueWatchMovieAndSeries()
                val responseBody = lastWatchedCall.execute()
//                Log.i("!!!", "LastWatched = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "LastWatched = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getSeriesListByCategoryName(categoryName: String, page: Int): SeriesList? {
        return withContext(ioDispatcher) {
            try {
                val seriesListCall =
                    sakhCastApiService.getSeriesListByCategoryName(categoryName, page)
                val responseBody = seriesListCall.execute()
                Log.i("!!!", "SeriesList from repo = ${responseBody.body()}")
                Log.i("!!!", "Call repo = $seriesListCall")
                Log.i("!!!", "SeriesList from repo = $responseBody")
                responseBody.body()
            } catch (e: Exception) {
                Log.i("!!!", "series homeScreen list = exception")
                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getSeriesListByGenre(page: Int, genre: String): SeriesList?{
        return withContext(ioDispatcher) {
            try {
                val seriesListCall =
                    sakhCastApiService.getSeriesListByGenre(page = page, genres = genre)
                val responseBody = seriesListCall.execute()
                Log.i("!!!", "SeriesList BY GENRE from repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                Log.i("!!!", "series list BY GENRE = exception")
                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getMoviesListByCategoryName(categoryName: String, page: Int): MovieList? {
        return withContext(ioDispatcher) {
            try {
                val moviesListCall = sakhCastApiService.getMoviesByCategoryName(categoryName, page)
                val responseBody = moviesListCall.execute()
//                Log.i("!!!", "MoviesList from repo = ${responseBody.body()}")
//                Log.i("!!!", "MoviesList code from repo = ${responseBody.code()}")

                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "movies homeScreen list = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getSeriesById(seriesId: Int): Series? {
        return withContext(ioDispatcher) {
            try {
                val seriesCall = sakhCastApiService.getSeriesById(seriesId)
                val responseBody = seriesCall.execute()
//                Log.i("!!!", "SeriesById = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "seriesById = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getMovieByAlphaId(movieAlphaId: String): Movie? {
        return withContext(ioDispatcher) {
            try {
                val movieCall = sakhCastApiService.getMovieByAlphaId(movieAlphaId)
                val responseBody = movieCall.execute()
//                Log.i("!!!", "MovieById from repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "movieById = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getSeriesEpisodesBySeasonId(seasonId: Int): List<Episode>? {
        return withContext(ioDispatcher) {
            try {
                val episodesListCall = sakhCastApiService.getSeriesEpisodesBySeasonId(seasonId)
                val responseBody = episodesListCall.execute()
//                Log.i("!!!", "Episodes from repo = ${responseBody.body()}")
//                Log.i("!!!", "SEASON_ID REPO = ${seasonId}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "Episodes = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getMovieRecommendationsByRefId(refMovieId: Int): MovieList? {
        return withContext(ioDispatcher) {
            try {
                val movieRecommendationsListCall =
                    sakhCastApiService.getMovieRecommendationsByRefId(refMovieId = refMovieId)
                val responseBody = movieRecommendationsListCall.execute()
//                Log.i("!!!", "Recommendations list from repo = ${responseBody.body()}")
//                Log.i("!!!", "refMovieId REPO = ${refMovieId}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "Episodes = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getMovieFavorites(): MovieList? {
        return withContext(ioDispatcher) {
            try {
                val movieFavoritesListCall =
                    sakhCastApiService.getMovieFavorites(page = 0)
                val responseBody = movieFavoritesListCall.execute()
//                Log.i("!!!", "MoviesFavorites from repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "MoviesFavorites from repo = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getSeriesFavorites(kind: String): SeriesList? {
        return withContext(ioDispatcher) {
            try {
                val seriesFavoritesListCall =
                    sakhCastApiService.getSeriesFavorites(page = 0, kind = kind)
                val responseBody = seriesFavoritesListCall.execute()
//                Log.i("!!!", "SeriesFavorites from repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "SeriesFavoritesFrom repo = exception, KIND = $kind")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

//    fun getSeriesPagingData(categoryName: String): Flow<PagingData<SeriesCard>> {
//        return Pager(PagingConfig(pageSize = 40)) {
//            SeriesPagingSource(sakhCastApiService, categoryName)
//        }.flow
//    }

}