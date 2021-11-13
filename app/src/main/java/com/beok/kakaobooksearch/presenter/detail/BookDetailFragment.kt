package com.beok.kakaobooksearch.presenter.detail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.beok.kakaobooksearch.R
import com.beok.kakaobooksearch.base.BaseFragment
import com.beok.kakaobooksearch.databinding.FragmentBookDetailBinding
import com.beok.kakaobooksearch.presenter.main.MainViewModel
import com.beok.kakaobooksearch.presenter.search.vo.DocumentVO
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
            val item = arguments?.get(BUNDLE_KEY_ITEM) as? DocumentVO ?: return@setOnCheckedChangeListener
            viewModel.likeItem(item = item, isLike = isChecked)
        }
    }

    private fun setupBinding() {
        binding.item = arguments?.get(BUNDLE_KEY_ITEM) as? DocumentVO
    }
    
    companion object {
        private const val BUNDLE_KEY_ITEM = "bundle_key_item"

        fun newInstance(item: DocumentVO): BookDetailFragment = BookDetailFragment().apply {
            arguments = bundleOf(BUNDLE_KEY_ITEM to item)
        }
    }
}
