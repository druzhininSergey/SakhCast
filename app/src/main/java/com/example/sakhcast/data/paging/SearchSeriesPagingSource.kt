package com.example.sakhcast.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.SeriesCard

class SearchSeriesPagingSource(
    private val sakhCastRepository: SakhCastRepository,
    private val textInput: String
) : PagingSource<Int, SeriesCard>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SeriesCard> {
        try {
            val page = params.key ?: 0
            val seriesList = searchSeries(textInput, page)
            Log.i("!!!", "Loaded seriesList size: ${seriesList?.items?.size}")

            val nextKey = if (seriesList?.items?.size == 40) page + 1 else null
            Log.i("!!!", "nextKey = $nextKey")

            return LoadResult.Page(
                data = seriesList?.items ?: emptyList(),
                prevKey = if (page == 0) null else page - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private suspend fun searchSeries(textInput: String, page: Int) =
        sakhCastRepository.searchSeries(textInput, page)


    override fun getRefreshKey(state: PagingState<Int, SeriesCard>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            Log.i("!!!", "Refresh anchorPosition: $anchorPosition")
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}