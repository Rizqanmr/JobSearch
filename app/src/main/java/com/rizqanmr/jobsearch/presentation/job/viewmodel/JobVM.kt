package com.rizqanmr.jobsearch.presentation.job.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.rizqanmr.jobsearch.data.JobPagingSource
import com.rizqanmr.jobsearch.data.network.model.JobItem
import com.rizqanmr.jobsearch.data.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JobVM @Inject constructor(
    private val jobRepository: JobRepository
) : JobVMContract, ViewModel() {

    override fun getListJobs(
        description: String?,
        location: String?,
        fullTime: String?
    ): LiveData<PagingData<JobItem>> {
        return Pager(
            config = PagingConfig(pageSize = 5, prefetchDistance = 1),
            pagingSourceFactory = { JobPagingSource(jobRepository, description, location, fullTime) }
        ).liveData.cachedIn(viewModelScope)
    }
}