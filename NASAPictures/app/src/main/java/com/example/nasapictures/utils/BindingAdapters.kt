package com.example.nasapictures.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.nasapictures.R

@BindingAdapter(value = ["app:imageUrl"], requireAll = true)
fun loadUrl(view: ImageView, url: String) {

    val factory =
        DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

    val requestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    Glide.with(view.context)
        .load(url)
        .transition(withCrossFade(factory))
        .centerCrop()
        .thumbnail(0.1f)
        .apply(requestOptions)
        .error(R.drawable.image_bg_default)
        .into(view)
}

@BindingAdapter("app:setText")
fun setText(textView: TextView, text: String?) {
    text?.let {
        textView.visibility = View.VISIBLE
        textView.text = it
    } ?: run {
        textView.visibility = View.GONE
    }

}

@BindingAdapter("app:setHtmlText")
fun setHtmlText(textView: TextView, text: String?) {

    text?.let {
        textView.visibility = View.VISIBLE
        textView.text = HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY)
    } ?: run {
        textView.visibility = View.GONE
    }
}

@BindingAdapter("app:setVisibility")
fun setVisibility(view: View, status: Boolean) {
    if (status) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}
