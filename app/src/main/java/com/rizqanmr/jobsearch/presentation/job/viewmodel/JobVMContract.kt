package com.rizqanmr.jobsearch.presentation.job.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.rizqanmr.jobsearch.data.network.model.JobItem

interface JobVMContract {

    fun getListJobs(): LiveData<PagingData<JobItem>>

    fun setSearchParameters(
        description: String?,
        location: String?,
        fullTime: String?
    )
}