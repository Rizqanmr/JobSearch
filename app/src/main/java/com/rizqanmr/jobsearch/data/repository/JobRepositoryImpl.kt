package com.rizqanmr.jobsearch.data.repository

import com.rizqanmr.jobsearch.data.network.ApiService
import javax.inject.Inject

class JobRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : JobRepository {
}