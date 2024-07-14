package com.rizqanmr.jobsearch.data.network

import com.rizqanmr.jobsearch.data.network.model.JobItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("recruitment/positions.json")
    suspend fun getListJobs(
        @Query("page") page: Int,
        @Query("description") description: String?,
        @Query("location") location: String?,
        @Query("full_time") fullTime: String?
    ): List<JobItem>

    @GET("recruitment/positions/{id}")
    suspend fun getJobDetail(
        @Path("id") id: String
    ): JobItem
}