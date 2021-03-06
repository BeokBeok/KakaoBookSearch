package com.beok.kakaobooksearch.presenter.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beok.kakaobooksearch.base.BaseViewModel
import com.beok.kakaobooksearch.domain.usecase.BookTitleSearchUseCase
import com.beok.kakaobooksearch.domain.usecase.BookTitleSearchUseCaseImpl
import com.beok.kakaobooksearch.presenter.search.vo.DocumentVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val bookTitleSearchUseCase: BookTitleSearchUseCase
): BaseViewModel() {

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
    ) = viewModelScope.launch(coroutineExceptionHandler) {
        clearDocument(isClear = !isNext)
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
                    it.document.map(DocumentVO::fromDomain)
                )
                isEnd = it.isEnd
            }
            .onFailure {
                hideLoading()
                error.value = it
            }
    }

    fun likeItem(item: DocumentVO, isLike: Boolean) {
        _document.value = _document.value
            ?.map {
                if (it.isbn == item.isbn) it.copy(isLike = isLike) else it
            }
    }

    private fun clearDocument(isClear: Boolean) {
        if (!isClear) return

        _document.value = emptyList()
        page = 1
        isEnd = false
    }

    private fun hideLoading() {
        _isLoading.value = false
    }

    private fun showLoading() {
        _isLoading.value = true
    }

}
