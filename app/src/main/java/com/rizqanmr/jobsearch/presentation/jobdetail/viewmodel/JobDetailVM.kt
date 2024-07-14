package com.rizqanmr.jobsearch.presentation.jobdetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizqanmr.jobsearch.data.network.Result
import com.rizqanmr.jobsearch.data.network.model.JobItem
import com.rizqanmr.jobsearch.data.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobDetailVM @Inject constructor(
    private val jobRepository: JobRepository
) : JobDetailVMContract, ViewModel() {

    private val isLoading = MutableLiveData<Boolean>()
    private val jobDetailLiveData: MutableLiveData<JobItem> = MutableLiveData()
    private val errorJobDetailLiveData: MutableLiveData<String> = MutableLiveData()

    override fun getIsLoading(): LiveData<Boolean> = isLoading

    override fun setIsLoading(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }

    override fun getJobDetail(id: String): Job = viewModelScope.launch {
        setIsLoading(true)

        when (val result = jobRepository.getJobDetail(id)) {
            is Result.Success -> jobDetailLiveData.value = result.data
            is Result.Error -> errorJobDetailLiveData.value = result.exception.localizedMessage
        }

        setIsLoading(false)
    }

    override fun jobDetailLiveData(): MutableLiveData<JobItem> = jobDetailLiveData

    override fun errorJobDetailLiveData(): LiveData<String> = errorJobDetailLiveData
}