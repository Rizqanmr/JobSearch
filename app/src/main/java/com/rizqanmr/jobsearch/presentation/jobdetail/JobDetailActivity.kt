package com.rizqanmr.jobsearch.presentation.jobdetail

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.rizqanmr.jobsearch.R
import com.rizqanmr.jobsearch.databinding.ActivityJobDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJobDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()

        val id = intent?.getStringExtra("id")
        if (!id.isNullOrEmpty()) {
            Toast.makeText(this, "id: $id", Toast.LENGTH_SHORT).show()
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
}