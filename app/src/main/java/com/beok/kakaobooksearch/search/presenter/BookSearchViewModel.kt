package com.beok.kakaobooksearch.search.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.kakaobooksearch.search.domain.usecase.BookTitleSearchUseCase
import com.beok.kakaobooksearch.search.domain.usecase.BookTitleSearchUseCaseImpl
import com.beok.kakaobooksearch.search.presenter.vo.DocumentVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
internal class BookSearchViewModel @Inject constructor(
    private val bookTitleSearchUseCase: BookTitleSearchUseCase
) : ViewModel() {

    private val _document = MutableLiveData<List<com.beok.kakaobooksearch.search.presenter.vo.DocumentVO>>()
    val document: LiveData<List<com.beok.kakaobooksearch.search.presenter.vo.DocumentVO>> get() = _document

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var page: Int = 1
    private var isEnd: Boolean = false

    fun searchByBookName(
        bookName: String,
        isNext: Boolean = false
    ) = viewModelScope.launch {
        if (!isNext) {
            _document.value = emptyList()
            page = 1
            isEnd = false
        }
        if (isEnd) return@launch

        showLoading()
        bookTitleSearchUseCase
            .execute(
                param = BookTitleSearchUseCaseImpl.Param(
                    query = bookName,
                    page = if (isNext) ++page else page
                )
            )
            .onSuccess {
                hideLoading()
                _document.value = (_document.value ?: emptyList()).plus(
                    it.document.map(com.beok.kakaobooksearch.search.presenter.vo.DocumentVO::fromDomain)
                )
                isEnd = it.isEnd
            }
    }

    private fun hideLoading() {
        _isLoading.value = false
    }

    private fun showLoading() {
        _isLoading.value = true
    }
}
