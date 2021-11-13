package com.beok.kakaobooksearch.search

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.beok.kakaobooksearch.base.BaseFragment
import com.beok.kakaobooksearch.base.BaseListAdapter
import com.beok.kakaobooksearch.BR
import com.beok.kakaobooksearch.R
import com.beok.kakaobooksearch.databinding.FragmentBookSearchBinding
import com.beok.kakaobooksearch.detail.BookDetailFragment
import com.beok.kakaobooksearch.ext.launchAndRepeatOnLifecycle
import com.beok.kakaobooksearch.ext.textChanges
import com.beok.kakaobooksearch.main.MainViewModel
import com.beok.kakaobooksearch.search.vo.DocumentVO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot

@AndroidEntryPoint
class BookSearchFragment : BaseFragment<FragmentBookSearchBinding>(R.layout.fragment_book_search) {

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBinding()
        setupUI()
        setupListener()
        showContent()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.clickedItem.observe(viewLifecycleOwner) {
            childFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(binding.clBookSearchDetail.id, BookDetailFragment::class.java, null)
                .commitAllowingStateLoss()
        }
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
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
            if (childFragmentManager.backStackEntryCount > 0) {
                childFragmentManager.popBackStack()
            } else {
                activity?.finish()
            }
        }
    }

    private fun setupUI() {
        binding.rvBookSearchContent.adapter = BaseListAdapter(
            layoutResourceID = R.layout.recycler_view_item_book,
            bindingID = BR.item,
            viewModel = mapOf(BR.viewModel to viewModel),
            diffUtil = object : DiffUtil.ItemCallback<DocumentVO>() {
                override fun areItemsTheSame(oldItem: DocumentVO, newItem: DocumentVO): Boolean =
                    oldItem.isbn == newItem.isbn

                override fun areContentsTheSame(oldItem: DocumentVO, newItem: DocumentVO): Boolean =
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
