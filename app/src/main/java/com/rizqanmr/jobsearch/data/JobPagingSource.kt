package com.rizqanmr.jobsearch.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rizqanmr.jobsearch.data.network.Result
import com.rizqanmr.jobsearch.data.network.model.JobItem
import com.rizqanmr.jobsearch.data.repository.JobRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JobPagingSource(
    private val repository: JobRepository,
    private val description: String?,
    private val location: String?,
    private val fullTime: String?
) : PagingSource<Int, JobItem>() {

    override fun getRefreshKey(state: PagingState<Int, JobItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JobItem> {
        return try {
            val nextPageNumber = params.key ?: 1

            val result = withContext(Dispatchers.IO) {
                repository.getListJobs(nextPageNumber, description, location, fullTime)
            }

            when (result) {
                is Result.Success -> {
                    val data = result.data
                    LoadResult.Page(
                        data = data,
                        prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                        nextKey = if (data.isEmpty()) null else nextPageNumber + 1
                    )
                }
                is Result.Error -> {
                    LoadResult.Error(result.exception)
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}