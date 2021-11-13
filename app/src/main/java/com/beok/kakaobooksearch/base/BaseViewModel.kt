package com.beok.kakaobooksearch.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel : ViewModel() {

    protected val error = MutableLiveData<Throwable>()
    val errorState: LiveData<Throwable> get() = error

    protected val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        error.value = throwable
    }
}
