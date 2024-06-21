package com.example.sakhcast.data.repository

import android.util.Log
import com.example.sakhcast.data.api_service.SakhCastApiService
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
//                Log.i("!!!", "Login response body: ${responseBody.code()}")
//                Log.i("!!!", "Login response body: $responseBody")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "Login from repo = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun userLogout(): Result? {
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

    suspend fun checkLoginStatus(): CurrentUser? {
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
                Log.i("!!!", "LastWatched = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                Log.i("!!!", "LastWatched = exception")
                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    // SERIES
    suspend fun getSeriesListByCategoryName(categoryName: String, page: Int): SeriesList? {
        return withContext(ioDispatcher) {
            try {
                val seriesListCall =
                    sakhCastApiService.getSeriesListByCategoryName(categoryName, page)
                val responseBody = seriesListCall.execute()
//                Log.i("!!!", "SeriesList from repo = ${responseBody.body()}")
//                Log.i("!!!", "Call repo = $seriesListCall")
//                Log.i("!!!", "SeriesList from repo = $responseBody")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "series homeScreen list = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getSeriesListByGenre(page: Int, genre: String): SeriesList? {
        return withContext(ioDispatcher) {
            try {
                val seriesListCall =
                    sakhCastApiService.getSeriesListByGenre(page = page, genres = genre)
                val responseBody = seriesListCall.execute()
//                Log.i("!!!", "SeriesList BY GENRE from repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "series list BY GENRE = exception")
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

    suspend fun addSeriesInFavorites(seriesId: Int, kind: String): String? {
        return withContext(ioDispatcher) {
            try {
                val addSeriesInFavCall =
                    sakhCastApiService.addSeriesInFavorites(seriesId = seriesId, kind = kind)
                val responseBody = addSeriesInFavCall.execute()
//                Log.i("!!!", "addSeriesInFavorites = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun removeSeriesFromFavorites(seriesId: Int): String? {
        return withContext(ioDispatcher) {
            try {
                val removeSeriesFromFavCall =
                    sakhCastApiService.removeSeriesFromFavorites(seriesId = seriesId)
                val responseBody = removeSeriesFromFavCall.execute()
//                Log.i("!!!", "removeSeriesFromFavorites repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "removeSeriesFromFavorites repo = exception, seriesId = $seriesId")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun searchSeries(textInput: String, page: Int): SeriesList? {
        return withContext(ioDispatcher) {
            try {
                val notificationListCall =
                    sakhCastApiService.searchSeries(textInput = textInput, page = page)
                val responseBody = notificationListCall.execute()
                Log.i("!!!", "seriesList Search repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                Log.i("!!!", "seriesList Search repo = exception")
                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    //MOVIES
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

    suspend fun getMovieByAlphaId(movieAlphaId: String): Movie? {
        return withContext(ioDispatcher) {
            try {
                val movieCall = sakhCastApiService.getMovieByAlphaId(movieAlphaId)
                val responseBody = movieCall.execute()
//                Log.i("!!!", "MovieById from repo = ${responseBody.body()}")
//                Log.i("!!!", "MovieById from repo = ${responseBody.code()}")
//                Log.i("!!!", "MovieById from repo = $responseBody")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "movieById = exception")
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

    suspend fun getMoviesListBySortField(sortField: String, page: Int): MovieList? {
        return withContext(ioDispatcher) {
            try {
                val movieListCall =
                    sakhCastApiService.getMoviesListBySortField(sortField = sortField, page = page)
                val responseBody = movieListCall.execute()
//                Log.i("!!!", "SeriesFavorites from repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "SeriesFavoritesFrom repo = exception, sortField = $sortField")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getMoviesListByGenreId(genresId: String, page: Int): MovieList? {
        return withContext(ioDispatcher) {
            try {
                val seriesFavoritesListCall =
                    sakhCastApiService.getMoviesListByGenreId(genresId = genresId, page = page)
                val responseBody = seriesFavoritesListCall.execute()
//                Log.i("!!!", "SeriesFavorites from repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "SeriesFavoritesFrom repo = exception, genresId = $genresId")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun putMovieInFavorites(movieAlphaId: String, kind: String): Result? {
        return withContext(ioDispatcher) {
            try {
                val addMovieInFavCall =
                    sakhCastApiService.putMovieInFavorites(
                        movieAlphaId = movieAlphaId,
                        kind = kind
                    )
                val responseBody = addMovieInFavCall.execute()
//                Log.i("!!!", "addMovieInFavCall REPO = $addMovieInFavCall")
//                Log.i("!!!", "addMovieInFavorites REPO= ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun deleteMovieFromFavorites(movieAlphaId: String): Result? {
        return withContext(ioDispatcher) {
            try {
                val deleteMovieFromFavCall =
                    sakhCastApiService.deleteMovieFromFavorites(movieAlphaId = movieAlphaId)
                val responseBody = deleteMovieFromFavCall.execute()
//                Log.i("!!!", "deleteMovieFromFavorites repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "movieFromFavorites repo = exception, movieId = $movieAlphaId")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getNotificationsList(): NotificationList? {
        return withContext(ioDispatcher) {
            try {
                val notificationListCall =
                    sakhCastApiService.getNotificationsList()
                val responseBody = notificationListCall.execute()
//                Log.i("!!!", "notificationList repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "notificationList repo = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun makeAllNotificationsRead(): Boolean? {
        return withContext(ioDispatcher) {
            try {
                val notificationListCall =
                    sakhCastApiService.makeAllNotificationsRead()
                val responseBody = notificationListCall.execute()
//                Log.i("!!!", "makeAllNotificationsRead repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "makeAllNotificationsRead repo = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun searchMovie(textInput: String, page: Int): MovieList? {
        return withContext(ioDispatcher) {
            try {
                val notificationListCall =
                    sakhCastApiService.searchMovie(textInput = textInput, page = page)
                val responseBody = notificationListCall.execute()
//                Log.i("!!!", "movieList Search repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
//                Log.i("!!!", "movieList Search repo = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun setMoviePosition(alphaId: String, positionSec: Int): Boolean? {
        return withContext(ioDispatcher) {
            try {
                val notificationListCall =
                    sakhCastApiService.setMoviePosition(alphaId, positionSec)
                val responseBody = notificationListCall.execute()
                responseBody.body()
            } catch (e: Exception) {
                null
            }
        }
    }

}