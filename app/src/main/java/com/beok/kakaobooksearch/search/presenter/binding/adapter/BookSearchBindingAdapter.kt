package com.beok.kakaobooksearch.search.presenter.binding.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind_imgSrc")
fun setImgSrc(view: ImageView, url: String) {
    Glide
        .with(view)
        .load(url)
        .into(view)
}
