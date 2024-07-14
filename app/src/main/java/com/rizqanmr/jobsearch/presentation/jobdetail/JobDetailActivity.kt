package com.rizqanmr.jobsearch.presentation.jobdetail

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.rizqanmr.jobsearch.R
import com.rizqanmr.jobsearch.data.network.model.JobItem
import com.rizqanmr.jobsearch.databinding.ActivityJobDetailBinding
import com.rizqanmr.jobsearch.presentation.jobdetail.viewmodel.JobDetailVM
import com.rizqanmr.jobsearch.utils.contentFromHtml
import com.rizqanmr.jobsearch.utils.setFitImageUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobDetailBinding
    private val viewModel: JobDetailVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJobDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        setupToolbar()
        setupObservers()
    }

    private fun getIntentData() {
        val id = intent?.getStringExtra("id")
        if (!id.isNullOrEmpty()) {
            viewModel.getJobDetail(id)
        }
    }

    private fun setupToolbar() {
        val toolbar = binding.toolbarJobDetail
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            val upArrow = ContextCompat.getDrawable(this@JobDetailActivity, R.drawable.ic_arrow_back)
            upArrow?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
            setHomeAsUpIndicator(upArrow)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun setupObservers() {
        viewModel.getIsLoading().observe(this) {
            showLoading(it)
        }
        viewModel.jobDetailLiveData().observe(this) {
            mappingJobDetail(it)
        }
        viewModel.errorJobDetailLiveData().observe(this) {
            Snackbar.make(binding.root, it.toString(), Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun mappingJobDetail(jobItem: JobItem) {
        with(binding) {
            item = jobItem
            imageCompany.setFitImageUrl(jobItem.companyLogo, R.drawable.ic_error)
            tvDescription.contentFromHtml(jobItem.description.orEmpty())
            tvHowToApply.contentFromHtml(jobItem.howToApply.orEmpty())
            tvUrl.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(jobItem.companyUrl) }
                startActivity(intent)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbLoading.isVisible = isLoading
    }
}