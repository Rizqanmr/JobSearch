package com.rizqanmr.jobsearch.utils

import android.text.Html
import android.text.Spanned
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizqanmr.jobsearch.R

fun ImageView.setFitImageUrl(url: String?, placeholder: Int?= null) {
    if (url.isNullOrBlank() && (placeholder == null || placeholder == 0)) return

    val glideRequest = Glide.with(context).load(url).fitCenter()
    placeholder?.let {
        glideRequest.placeholder(it).apply(
            RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
        )
    }
    glideRequest.into(this)
}

fun ImageView.setCircleImageUrl(url: String?, placeholder: Int?= null) {
    if (url.isNullOrBlank() && (placeholder == null || placeholder == 0)) return

    val glideRequest = Glide.with(context).load(url).circleCrop()
    placeholder?.let {
        glideRequest.placeholder(it)
    }
    glideRequest.into(this)
}

fun TextView.contentFromHtml(source: String) {
    val spanned: Spanned = Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
    this.text = spanned
}