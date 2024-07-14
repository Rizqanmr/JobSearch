package com.rizqanmr.jobsearch.presentation.job

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.rizqanmr.jobsearch.data.network.model.JobItem
import com.rizqanmr.jobsearch.databinding.ItemJobBinding

class JobAdapter : PagingDataAdapter<JobItem, JobViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<JobItem>() {
            override fun areItemsTheSame(oldItem: JobItem, newItem: JobItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: JobItem, newItem: JobItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    private lateinit var jobListener: JobListener

    fun setJobListener(jobListener: JobListener) {
        this.jobListener = jobListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bindData(item, jobListener)
        }
    }

    interface JobListener {
        fun onItemClick(itemJob: ItemJobBinding, itemResponse: JobItem?)
    }
}