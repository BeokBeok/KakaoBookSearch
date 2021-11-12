package com.beok.search.ext

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun launchAndRepeatOnLifecycle(
    scope: LifecycleCoroutineScope,
    owner: LifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.CREATED,
    action: suspend CoroutineScope.() -> Unit
) {
    scope.launch {
        owner.repeatOnLifecycle(state) {
            action()
        }
    }
}
