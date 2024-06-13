package com.example.sakhcast.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.SeriesCard
import com.example.sakhcast.model.SeriesList

class SeriesPagingSource(
    private val sakhCastRepository: SakhCastRepository,
    private val categoryName: String
) : PagingSource<Int, SeriesCard>() {

    private val categoryList = mapOf(
        "Все" to "all",
        "Новинки" to "new",
        "Российский топ" to "top_kp",
        "Мировой топ" to "top_imdb",
        "Сейчас смотрят" to "popular",
        "По алфавиту" to "abc",
    )
    private val genreList = mapOf(
        "Мини–сериалы" to "мини–сериал",
        "Документальные" to "документальный",
        "Подкасты" to "подкаст",
        "Аниме" to "аниме",
        "Мультсериалы" to "мультфильм",
        "Комедии" to "комедия"
    )

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SeriesCard> {
        try {
            val page = params.key ?: 0
            val categoryNameUrl = getCategoryNameUrl(categoryName)
            Log.i("!!!", "Loading page: $page, categoryName: $categoryNameUrl")
            val seriesList = getSeriesList(page, categoryNameUrl)
            Log.i("!!!", "Loaded seriesList size: ${seriesList?.items?.size}")


            val nextKey = if (seriesList?.items?.size == 20) page + 1 else null
            Log.i("!!!", "nextkey = $nextKey")

            return LoadResult.Page(
                data = seriesList?.items ?: emptyList(),
                prevKey = if (page == 0) null else page - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private fun getCategoryNameUrl(categoryName: String): String {
        return categoryList[categoryName] ?: genreList[categoryName]
        ?: throw IllegalArgumentException("Unknown category or genre: $categoryName")
    }

    private suspend fun getSeriesList(page: Int, categoryNameUrl: String): SeriesList? {
        return if (categoryName in categoryList) {
            sakhCastRepository.getSeriesListByCategoryName(categoryNameUrl, page)
        } else {
            sakhCastRepository.getSeriesListByGenre(genre = categoryNameUrl, page =  page)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SeriesCard>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            Log.i("!!!", "Refresh anchorPosition: $anchorPosition")
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}