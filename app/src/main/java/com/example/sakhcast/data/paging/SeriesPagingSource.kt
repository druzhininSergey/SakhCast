package com.example.sakhcast.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sakhcast.data.api_service.SackCastApiService
import com.example.sakhcast.model.SeriesCard
import com.example.sakhcast.model.SeriesList
import retrofit2.HttpException
import java.io.IOException

class SeriesPagingSource(
    private val categoryName: String,
    private val service: SackCastApiService
) : PagingSource<Int, SeriesCard>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SeriesCard> {
        return try {
            val page = params.key ?: 1 // Начальная страница 1
            val response = service.getSeriesListByCategoryName(categoryName, page)
            val series = response.execute().body()?.items ?: emptyList()

            LoadResult.Page(
                data = series,
                prevKey = if (page > 1) page - 1 else null,
                nextKey = if (series.isNotEmpty()) page + 1 else null
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SeriesCard>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}