package com.rizqanmr.jobsearch.presentation.jobdetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rizqanmr.jobsearch.data.network.model.JobItem
import kotlinx.coroutines.Job

interface JobDetailVMContract {

    fun getIsLoading(): LiveData<Boolean>

    fun setIsLoading(isLoading: Boolean)

    fun getJobDetail(id: String): Job

    fun jobDetailLiveData(): MutableLiveData<JobItem>

    fun errorJobDetailLiveData(): LiveData<String>
}