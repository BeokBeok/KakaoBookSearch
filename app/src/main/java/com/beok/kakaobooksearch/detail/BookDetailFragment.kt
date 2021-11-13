package com.beok.kakaobooksearch.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.beok.kakaobooksearch.R
import com.beok.kakaobooksearch.base.BaseFragment
import com.beok.kakaobooksearch.databinding.FragmentBookDetailBinding
import com.beok.kakaobooksearch.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailFragment : BaseFragment<FragmentBookDetailBinding>(R.layout.fragment_book_detail) {

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBinding()
        setupListener()
    }

    private fun setupListener() {
        binding.cbBookDetailLike.setOnCheckedChangeListener { _, isChecked ->
            viewModel.likeItem(
                item = viewModel.clickedItem.value ?: return@setOnCheckedChangeListener,
                isLike = isChecked
            )
        }
    }

    private fun setupBinding() {
        binding.viewModel = viewModel
    }
}
