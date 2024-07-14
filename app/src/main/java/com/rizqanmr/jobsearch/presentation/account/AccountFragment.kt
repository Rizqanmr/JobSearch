package com.rizqanmr.jobsearch.presentation.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rizqanmr.jobsearch.R
import com.rizqanmr.jobsearch.databinding.FragmentAccountBinding
import com.rizqanmr.jobsearch.presentation.account.viewmodel.AccountVM
import com.rizqanmr.jobsearch.utils.setCircleImageUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private val viewModel: AccountVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textUsername.text = it
        }
        viewModel.imageUrl.observe(viewLifecycleOwner) {
            binding.ivAvatar.setCircleImageUrl(it, R.drawable.ic_person)
        }
    }
}