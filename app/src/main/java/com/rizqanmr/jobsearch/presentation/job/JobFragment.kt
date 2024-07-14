package com.rizqanmr.jobsearch.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rizqanmr.jobsearch.data.network.model.JobItem
import com.rizqanmr.jobsearch.databinding.FragmentJobBinding
import com.rizqanmr.jobsearch.databinding.ItemJobBinding
import com.rizqanmr.jobsearch.presentation.job.viewmodel.JobVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobFragment : Fragment() {

    private lateinit var binding: FragmentJobBinding
    private lateinit var jobAdapter: JobAdapter
    private val viewModel: JobVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        subscribeToLiveData()
    }
}