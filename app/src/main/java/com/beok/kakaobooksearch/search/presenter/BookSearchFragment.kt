package com.beok.kakaobooksearch.search.presenter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.beok.common.base.BaseFragment
import com.beok.common.base.BaseListAdapter
import com.beok.kakaobooksearch.BR
import com.beok.kakaobooksearch.R
import com.beok.kakaobooksearch.databinding.FragmentBookSearchBinding
import com.beok.kakaobooksearch.ext.launchAndRepeatOnLifecycle
import com.beok.kakaobooksearch.ext.textChanges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot

@AndroidEntryPoint
class BookSearchFragment : BaseFragment<FragmentBookSearchBinding>(R.layout.fragment_book_search) {

    private val viewModel by viewModels<BookSearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBinding()
        setupUI()
        setupListener()
        showContent()
    }

    private fun setupBinding() {
        binding.viewModel = viewModel
    }

    private fun setupListener() {
        binding.rvBookSearchContent.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (dy < 0) return
                    val bottomDirection = 1
                    if (!recyclerView.canScrollVertically(bottomDirection)) {
                        viewModel.searchByBookName(
                            bookName = binding.etBookSearch.text.toString(),
                            isNext = true
                        )
                    }
                }
            }
        )
    }

    private fun setupUI() {
        binding.rvBookSearchContent.adapter = BaseListAdapter(
            layoutResourceID = R.layout.recycler_view_item_book,
            bindingID = BR.item,
            viewModel = mapOf(BR.viewModel to viewModel),
            diffUtil = object : DiffUtil.ItemCallback<com.beok.kakaobooksearch.search.presenter.vo.DocumentVO>() {
                override fun areItemsTheSame(oldItem: com.beok.kakaobooksearch.search.presenter.vo.DocumentVO, newItem: com.beok.kakaobooksearch.search.presenter.vo.DocumentVO): Boolean =
                    oldItem.isbn == newItem.isbn

                override fun areContentsTheSame(oldItem: com.beok.kakaobooksearch.search.presenter.vo.DocumentVO, newItem: com.beok.kakaobooksearch.search.presenter.vo.DocumentVO): Boolean =
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
