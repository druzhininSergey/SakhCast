package com.example.sakhcast.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.MovieCard
import com.example.sakhcast.model.MovieList

class MoviesPagingSource(
    private val sakhCastRepository: SakhCastRepository,
    private val categoryName: String
) : PagingSource<Int, MovieCard>() {
    private val categoryList = mapOf(
        "Все" to "Movie.sort",
        "Свежее" to "Movie.update_time",
        "Новинки" to "Movie.release_date",
        "Сейчас смотрят" to "Movie.popularity",
        "Российский топ" to "Movie.kp_rating",
        "Мировой топ" to "Movie.imdb_rating",
    )
    private val genreList = mapOf(
        "Боевики" to "6",
        "Комедии" to "4",
        "Мультфильмы" to "9",
        "Стендап" to "20",
    )

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieCard> {
        try {
            val page = params.key ?: 0
            val categoryNameUrl = getCategoryNameUrl(categoryName)
            Log.i("!!!", "Loading page: $page, categoryName: $categoryNameUrl")
            val moviesList = getMoviesList(page, categoryNameUrl)
            Log.i("!!!", "Loaded seriesList size: ${moviesList?.items?.size}")

            val nextKey = if (moviesList?.items?.size == 40) page + 1 else null
            Log.i("!!!", "nextKey = $nextKey")

            return LoadResult.Page(
                data = moviesList?.items ?: emptyList(),
                prevKey = if (page == 0) null else page - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieCard>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            Log.i("!!!", "Refresh anchorPosition: $anchorPosition")
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun getCategoryNameUrl(categoryName: String): String {
        return categoryList[categoryName] ?: genreList[categoryName] ?: categoryName
    }

    private suspend fun getMoviesList(page: Int, categoryNameUrl: String): MovieList? {
        return if (categoryName in categoryList) {
            sakhCastRepository.getMoviesListBySortField(categoryNameUrl, page)
        } else if (categoryName.endsWith(".company")) {
            val companyId = categoryName.substringBeforeLast(".company")
            sakhCastRepository.getMoviesListByCompanyId(companyId, page)
        } else if (categoryName.endsWith(".person")) {
            val personId = categoryName.substringBeforeLast(".person")
            sakhCastRepository.getMoviesListByPersonId(personId, page)
        } else if (categoryName.endsWith(".favorite")) {
            sakhCastRepository.getMovieFavorites(page)
        } else {
            sakhCastRepository.getMoviesListByGenreId(categoryNameUrl, page)
        }
    }

}