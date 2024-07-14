package com.rizqanmr.jobsearch.presentation.job

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
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

    private fun setupView() {
        with(binding) {
            jobAdapter = JobAdapter()
            rvJob.apply {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                setHasFixedSize(true)
                jobAdapter.addLoadStateListener { loadState ->
                    if (loadState.refresh is LoadState.Loading && jobAdapter.itemCount == 0) {
                        //loading state
                        rvJob.isVisible = false
                        pbLoading.isVisible = true
                    } else if (loadState.refresh is LoadState.NotLoading && jobAdapter.itemCount > 0) {
                        rvJob.isVisible = true
                        pbLoading.isVisible = false
                    } else if (loadState.refresh is LoadState.NotLoading && jobAdapter.itemCount == 0) {
                        //empty state
                        rvJob.isVisible = false
                        pbLoading.isVisible = false
                    }
                }
                adapter = jobAdapter.withLoadStateFooter(
                    footer = LoadingStateAdapter { jobAdapter.retry() }
                )
            }

            jobAdapter.setJobListener(object : JobAdapter.JobListener {
                override fun onItemClick(itemJob: ItemJobBinding, itemResponse: JobItem?) {
                    Toast.makeText(requireContext(), "title: ${itemResponse?.title}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun subscribeToLiveData() {
        viewModel.getListJobs().observe(viewLifecycleOwner) {
            if (it != null) {
                jobAdapter.submitData(lifecycle, it)
            }
        }
    }
}