package com.beok.kakaobooksearch.search.binding.adapter

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.beok.kakaobooksearch.R

@BindingAdapter("bind_srcBookmark")
fun setSrcBookmark(view: ImageView, isLike: Boolean) {
    view.setImageDrawable(
        ContextCompat.getDrawable(
            view.context,
            if (isLike) {
                R.drawable.ic_baseline_bookmark_24
            } else {
                R.drawable.ic_baseline_bookmark_border_24
            }
        )
    )
}
