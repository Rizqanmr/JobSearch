package com.rizqanmr.jobsearch.presentation.job.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
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

    private val searchParameters = MutableLiveData<Triple<String?, String?, String?>>()

    init {
        searchParameters.value = Triple(null, null, null)
    }

    override fun setSearchParameters(description: String?, location: String?, fullTime: String?) {
        searchParameters.value = Triple(description, location, fullTime)
    }

    override fun getListJobs(): LiveData<PagingData<JobItem>> = searchParameters.switchMap { params ->
        val (description, location, fullTime) = params
        Pager(
            config = PagingConfig(pageSize = 5, prefetchDistance = 1),
            pagingSourceFactory = { JobPagingSource(jobRepository, description, location, fullTime) }
        ).liveData.map { pagingData ->
            pagingData.filter { jobItem -> jobItem != null }
        }.cachedIn(viewModelScope)
    }
}