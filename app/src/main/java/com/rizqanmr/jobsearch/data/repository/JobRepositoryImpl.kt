package com.rizqanmr.jobsearch.data.repository

import com.rizqanmr.jobsearch.data.network.ApiService
import com.rizqanmr.jobsearch.data.network.Result
import com.rizqanmr.jobsearch.data.network.model.JobItem
import com.rizqanmr.jobsearch.utils.safeApiCall
import javax.inject.Inject

class JobRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : JobRepository {
    override suspend fun getListJobs(
        page: Int,
        description: String?,
        location: String?,
        fullTime: String?
    ): Result<List<JobItem>> = safeApiCall {
        apiService.getListJobs(page, description, location, fullTime)
    }

    override suspend fun getJobDetail(id: String): Result<JobItem> = safeApiCall {
        apiService.getJobDetail(id)
    }
}