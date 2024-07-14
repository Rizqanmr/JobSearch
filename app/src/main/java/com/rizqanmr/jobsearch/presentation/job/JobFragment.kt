package com.rizqanmr.jobsearch.presentation.job

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    private fun setupView() {
        jobAdapter = JobAdapter()
        binding.rvJob.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
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

    private fun subscribeToLiveData() {
        viewModel.getListJobs().observe(viewLifecycleOwner) {
            if (it != null) {
                jobAdapter.submitData(lifecycle, it)
            }
        }
    }
}