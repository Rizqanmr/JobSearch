package com.rizqanmr.jobsearch.data.repository

import com.rizqanmr.jobsearch.data.network.Result
import com.rizqanmr.jobsearch.data.network.model.JobItem

interface JobRepository {

    suspend fun getListJobs(
        page: Int,
        description: String? = "",
        location: String? = "",
        fullTime: String? = "false"
    ) : Result<List<JobItem>>

    suspend fun getJobDetail(id: String) : Result<JobItem>
}