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

    fun searchByBookName(
        bookName: String,
        page: Int = 1
    ) = viewModelScope.launch {
        bookTitleSearchUseCase
            .execute(
                param = BookTitleSearchUseCaseImpl.Param(
                    query = bookName,
                    page = page
                )
            )
            .onSuccess {
                _document.value = it.document.map(DocumentVO::fromDomain)
            }
            .onFailure {

            }
    }
}
