package com.beok.kakaobooksearch.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.kakaobooksearch.domain.usecase.BookTitleSearchUseCase
import com.beok.kakaobooksearch.domain.usecase.BookTitleSearchUseCaseImpl
import com.beok.kakaobooksearch.search.vo.DocumentVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val bookTitleSearchUseCase: BookTitleSearchUseCase
): ViewModel() {

    private val _document = MutableLiveData<List<DocumentVO>>()
    val document: LiveData<List<DocumentVO>> get() = _document

    private val _clickedItem = MutableLiveData<DocumentVO>()
    val clickedItem: LiveData<DocumentVO> get() = _clickedItem

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var page: Int = 1
    private var isEnd: Boolean = false

    fun onClickedItem(item: DocumentVO) {
        _clickedItem.value = item
    }

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
                    it.document.map(com.beok.kakaobooksearch.search.vo.DocumentVO::fromDomain)
                )
                isEnd = it.isEnd
            }
    }

    fun likeItem(item: DocumentVO, isLike: Boolean) {
        _document.value = _document.value
            ?.toList()
            ?.map {
                if (it.isbn == item.isbn) it.copy(isLike = isLike) else it
            }
    }

    private fun hideLoading() {
        _isLoading.value = false
    }

    private fun showLoading() {
        _isLoading.value = true
    }

}
