package com.beok.search.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.search.domain.usecase.BookTitleSearchUseCase
import com.beok.search.domain.usecase.BookTitleSearchUseCaseImpl
import com.beok.search.presenter.vo.DocumentVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
internal class BookSearchViewModel @Inject constructor(
    private val bookTitleSearchUseCase: BookTitleSearchUseCase
) : ViewModel() {

    private val _document = MutableLiveData<List<DocumentVO>>()
    val document: LiveData<List<DocumentVO>> get() = _document

    private var page: Int = 1
    private var isEnd: Boolean = false

    fun searchByBookName(
        bookName: String,
        isNext: Boolean = false
    ) = viewModelScope.launch {
        if (isEnd) return@launch
        if (isNext) {
            ++page
        } else {
            _document.value = emptyList()
            page = 1
        }
        bookTitleSearchUseCase
            .execute(
                param = BookTitleSearchUseCaseImpl.Param(
                    query = bookName,
                    page = page
                )
            )
            .onSuccess {
                _document.value = (_document.value ?: emptyList()).plus(
                    it.document.map(DocumentVO::fromDomain)
                )
                isEnd = it.isEnd
            }
    }
}
