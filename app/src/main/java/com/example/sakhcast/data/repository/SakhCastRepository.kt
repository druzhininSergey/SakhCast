package com.example.sakhcast.data.repository

import com.example.sakhcast.data.api_service.SakhCastApiService
import com.example.sakhcast.data.firebase_messaging.CrashReporter
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SakhCastRepository @Inject constructor(
    private val sakhCastApiService: SakhCastApiService,
    private val crashReporter: CrashReporter,
) {
    private val ioDispatcher: CoroutineContext = Dispatchers.IO

    suspend fun userLogin(loginInput: String, passwordInput: String): LoginResponse? {
        return withContext(ioDispatcher) {
            try {
                val loginCall = sakhCastApiService.userLogin(loginInput, passwordInput)
                val responseBody = loginCall.execute()
                responseBody.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in userLogin")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
                crashReporter.apply {
                    logError("Error in userLogout")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
                crashReporter.apply {
                    logError("Error in checkLoginStatus")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
                crashReporter.apply {
                    logError("Error in getContinueWatchMovieAndSeries")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
                crashReporter.apply {
                    logError("Error in getSeriesListByCategoryName")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
//                Log.i("!!!", "series homeScreen list = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getSeriesListByCompany(companyId: String, page: Int): SeriesList? {
        return withContext(ioDispatcher) {
            try {
                val seriesListCall =
                    sakhCastApiService.getSeriesListByCompany(page = page, networks = companyId)
                val responseBody = seriesListCall.execute()
//                Log.i("!!!", "SeriesList from repo = ${responseBody.body()}")
//                Log.i("!!!", "Call repo = $seriesListCall")
//                Log.i("!!!", "SeriesList from repo = $responseBody")
                responseBody.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in getSeriesListByCompany")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
//                Log.i("!!!", "SeriesList BY GENRE from repo = ${responseBody.code()}")
//                Log.i("!!!", "SeriesList BY GENRE from repo = ${responseBody}")
                responseBody.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in getSeriesListByGenre")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
//                Log.i("!!!", "seriesCall}")
                responseBody.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in getSeriesById")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
//                Log.i("!!!", "seriesById = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getSeriesFavorites(kind: String, page: Int = 0): SeriesList? {
        return withContext(ioDispatcher) {
            try {
                val seriesFavoritesListCall =
                    sakhCastApiService.getSeriesFavorites(page = page, kind = kind)
                val responseBody = seriesFavoritesListCall.execute()
//                Log.i("!!!", "SeriesFavorites from repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in getSeriesFavorites")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
//                Log.i("!!!", "episodesListCall")
                responseBody.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in getSeriesEpisodesBySeasonId")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
                crashReporter.apply {
                    logError("Error in addSeriesInFavorites")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
                crashReporter.apply {
                    logError("Error in removeSeriesFromFavorites")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
//                Log.i("!!!", "seriesList Search repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in searchSeries")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
//                Log.i("!!!", "seriesList Search repo = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getSeriesPlaylistBySeasonIdAndRgName(seasonId: String, rgName: String)
            : List<SeriesPlaylist>? {
        return withContext(ioDispatcher) {
            try {
                val notificationListCall =
                    sakhCastApiService.getSeriesPlaylistBySeasonIdAndRgName(seasonId, rgName)
                val responseBody = notificationListCall.execute()
//                Log.i("!!!", "playlist repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in getSeriesPlaylistBySeasonIdAndRgName")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
//                Log.i("!!!", "playlist repo = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun setSeriesEpisodePosition(episodeId: Int, positionSec: Int): Boolean? {
        return withContext(ioDispatcher) {
            try {
                val notificationListCall =
                    sakhCastApiService.setSeriesEpisodePosition(episodeId, positionSec)
                val responseBody = notificationListCall.execute()
                val getPositionCall = sakhCastApiService.getSeriesEpisodePosition(episodeId)
                getPositionCall.execute()
                responseBody.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in setSeriesEpisodePosition")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
                null
            }
        }
    }

// MOVIES

    suspend fun getMoviesListByCategoryName(categoryName: String, page: Int): MovieList? {
        return withContext(ioDispatcher) {
            try {
                val moviesListCall = sakhCastApiService.getMoviesByCategoryName(categoryName, page)
                val responseBody = moviesListCall.execute()
//                Log.i("!!!", "MoviesList from repo = ${responseBody.body()}")
//                Log.i("!!!", "MoviesList code from repo = ${responseBody.code()}")

                responseBody.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in getMoviesListByCategoryName")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
                crashReporter.apply {
                    logError("Error in getMovieByAlphaId")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
                crashReporter.apply {
                    logError("Error in getMovieRecommendationsByRefId")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
//                Log.i("!!!", "Episodes = exception")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getMovieFavorites(page: Int = 0, kind: String): MovieList? {
        return withContext(ioDispatcher) {
            try {
                val movieFavoritesListCall =
                    sakhCastApiService.getMovieFavorites(page = page, kind = kind)
                val responseBody = movieFavoritesListCall.execute()
//                Log.i("!!!", "MoviesFavorites from repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in getMovieFavorites")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
                crashReporter.apply {
                    logError("Error in getMoviesListBySortField")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
//                Log.i("!!!", "SeriesFavoritesFrom repo = exception, sortField = $sortField")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getMoviesListByCompanyId(companyId: String, page: Int): MovieList? {
        return withContext(ioDispatcher) {
            try {
                val movieListCall =
                    sakhCastApiService.getMoviesListByCompanyId(companyId = companyId, page = page)
                val responseBody = movieListCall.execute()
//                Log.i("!!!", "SeriesFavorites from repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in getMoviesListByCompanyId")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
//                Log.i("!!!", "SeriesFavoritesFrom repo = exception, sortField = $sortField")
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun getMoviesListByPersonId(personId: String, page: Int): MovieList? {
        return withContext(ioDispatcher) {
            try {
                val movieListCall =
                    sakhCastApiService.getMoviesListByPersonId(personId = personId, page = page)
                val responseBody = movieListCall.execute()
//                Log.i("!!!", "SeriesFavorites from repo = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in getMoviesListByPersonId")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
                crashReporter.apply {
                    logError("Error in getMoviesListByGenreId")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
                crashReporter.apply {
                    logError("Error in putMovieInFavorites")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
//                Log.i("!!!", "${e.message}")
                null
            }
        }
    }

    suspend fun changeMovieFavoritesType(movieAlphaId: String, kind: String): Result? {
        return withContext(ioDispatcher) {
            try {
                val addMovieInFavCall =
                    sakhCastApiService.changeMovieFavoritesType(
                        movieAlphaId = movieAlphaId,
                        kind = kind
                    )
                val responseBody = addMovieInFavCall.execute()
//                Log.i("!!!", "addMovieInFavCall REPO = $addMovieInFavCall")
//                Log.i("!!!", "addMovieInFavorites REPO= ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in putMovieInFavorites")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
                crashReporter.apply {
                    logError("Error in deleteMovieFromFavorites")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
                crashReporter.apply {
                    logError("Error in getNotificationsList")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
                crashReporter.apply {
                    logError("Error in makeAllNotificationsRead")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
                crashReporter.apply {
                    logError("Error in searchMovie")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
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
//                Log.i("!!!", "send position to backend = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in setMoviePosition")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
                null
            }
        }
    }

    suspend fun getMoviePosition(alphaId: String): Int? {
        return withContext(ioDispatcher) {
            try {
                val notificationListCall =
                    sakhCastApiService.getMoviePosition(alphaId)
                val responseBody = notificationListCall.execute()
                responseBody.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in getMoviePosition")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
                null
            }
        }
    }

    suspend fun fetchHlsManifest(uri: String): String? {
        return withContext(ioDispatcher) {
            try {
                val call = sakhCastApiService.fetchHlsManifest(uri)
                val response = call.execute()
                response.body()
            } catch (e: Exception) {
                crashReporter.apply {
                    logError("Error in fetchHlsManifest")
                    setCustomKey("error_message", e.message ?: "Unknown error")
                    recordException(e)
                }
                null
            }
        }
    }

}