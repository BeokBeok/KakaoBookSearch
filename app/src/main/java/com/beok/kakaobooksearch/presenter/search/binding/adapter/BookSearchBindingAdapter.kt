package com.beok.kakaobooksearch.presenter.search.binding.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.beok.kakaobooksearch.R
import com.beok.kakaobooksearch.util.DateConverter
import java.util.Date

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

@BindingAdapter(
    value = [
        "bind_setText_publisher",
        "bind_setText_date"
    ]
)
fun setPublisherAndDateText(view: TextView, publisher: String, date: Date) {
    view.text = when {
        publisher.isEmpty() -> DateConverter.toYYYYMM(date)
        else -> view.context.getString(
            R.string.publisher_and_date,
            publisher,
            DateConverter.toYYYYMM(date)
        )
    }
}
