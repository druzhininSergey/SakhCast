package com.example.sakhcast.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sakhcast.Genres
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

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SeriesCard> {

        try {
            val page = params.key ?: 0
            val categoryNameUrl = getCategoryNameUrl(categoryName)
            val seriesList = getSeriesList(page, categoryNameUrl)
            val nextKey = if (seriesList?.items?.size == 40) page + 1 else null

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
        return categoryList[categoryName] ?: Genres.seriesGenresMap[categoryName]
        ?: categoryName
    }

    private suspend fun getSeriesList(page: Int, categoryNameUrl: String): SeriesList? {
        return if (categoryName in categoryList) {
            sakhCastRepository.getSeriesListByCategoryName(categoryNameUrl, page)
        } else if (categoryName.endsWith(".company")) {
            val companyId = categoryName.substringBeforeLast(".company")
            sakhCastRepository.getSeriesListByCompany(companyId, page)
        }
//        else if (categoryName.endsWith(".person")) {
//            val personId = categoryName.substringBeforeLast(".person")
//            sakhCastRepository.getMoviesListByPersonId(personId, page)
//        }
        else if (categoryName.endsWith(".favorite")) {
            val companyId = categoryName.substringBeforeLast(".favorite")
            sakhCastRepository.getSeriesFavorites(companyId, page)
        } else {
            sakhCastRepository.getSeriesListByGenre(genre = categoryNameUrl, page = page)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SeriesCard>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}