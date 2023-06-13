package com.example.netflix.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.netflix.models.Movie
import com.example.netflix.request.Service
import retrofit2.HttpException

class MoviesPagingSource(
    private var service: Service
): PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageIndex = params.key ?: 1
            val response = service.getData(pageIndex)

            LoadResult.Page(
                data = response,
                nextKey = if(response.isEmpty()) null else pageIndex.plus(1),
                prevKey = if (pageIndex == 1) null else pageIndex.minus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        } catch (httpE: HttpException){
            LoadResult.Error(httpE)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}