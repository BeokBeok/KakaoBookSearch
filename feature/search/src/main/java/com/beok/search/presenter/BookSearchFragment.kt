package com.beok.search.presenter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import com.beok.common.base.BaseFragment
import com.beok.common.base.BaseListAdapter
import com.beok.search.BR
import com.beok.search.R
import com.beok.search.databinding.FragmentBookSearchBinding
import com.beok.search.domain.model.Document
import com.beok.search.ext.launchAndRepeatOnLifecycle
import com.beok.search.ext.textChanges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNot

@AndroidEntryPoint
class BookSearchFragment : BaseFragment<FragmentBookSearchBinding>(R.layout.fragment_book_search) {

    private val viewModel by viewModels<BookSearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showContent()
        setupUI()
    }

    private fun setupUI() {
        binding.rvBookSearchContent.adapter = BaseListAdapter(
            layoutResourceID = R.layout.recycler_view_item_book,
            bindingID = BR.item,
            viewModel = mapOf(BR.viewModel to viewModel),
            diffUtil = object : DiffUtil.ItemCallback<Document>() {
                override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean =
                    oldItem.isbn == newItem.isbn

                override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean =
                    oldItem == newItem
            }
        ).apply {
            viewModel.document.observe(viewLifecycleOwner) {
                submitList(it)
            }
        }
        binding.rvBookSearchContent.setHasFixedSize(true)
    }

    private fun showContent() {
        launchAndRepeatOnLifecycle(
            scope = lifecycleScope,
            owner = viewLifecycleOwner
        ) {
            binding.etBookSearch
                .textChanges()
                .filterNot(CharSequence?::isNullOrBlank)
                .debounce(300)
                .collectLatest {
                    viewModel.searchByBookName(it?.toString() ?: "")
                }
        }
    }
}
