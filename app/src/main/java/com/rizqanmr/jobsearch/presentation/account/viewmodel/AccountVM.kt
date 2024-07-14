package com.rizqanmr.jobsearch.presentation.account.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountVM @Inject constructor() : AccountVMContract, ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is account Fragment"
    }
    val text: LiveData<String> = _text

    private val _imageUrl = MutableLiveData<String>().apply {
        value = "https://picsum.photos/200"
    }

    val imageUrl: LiveData<String> = _imageUrl
}