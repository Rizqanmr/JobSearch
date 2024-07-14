package com.rizqanmr.jobsearch.presentation.job

import androidx.recyclerview.widget.RecyclerView
import com.rizqanmr.jobsearch.R
import com.rizqanmr.jobsearch.data.network.model.JobItem
import com.rizqanmr.jobsearch.databinding.ItemJobBinding
import com.rizqanmr.jobsearch.utils.setFitImageUrl

class JobViewHolder(private val binding: ItemJobBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(
        itemResponse: JobItem?,
        listener: JobAdapter.JobListener
    ) {
        (binding as? ItemJobBinding)?.let { itemJob ->
            itemJob.item = itemResponse
            with(itemJob) {
                imageJob.setFitImageUrl(itemResponse?.companyLogo, R.drawable.ic_error)
                cvJob.setOnClickListener { listener.onItemClick(itemJob, itemResponse)}
            }
        }
    }
}