package com.beok.kakaobooksearch.binding.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("bind_isVisible")
fun isVisible(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}
