package com.example.nasapictures.ui.detail

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.example.nasapictures.R
import com.example.nasapictures.databinding.DetailPictureItemBinding
import com.example.nasapictures.model.Picture

class PictureDetailViewHolder(private val binding: DetailPictureItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(picture: Picture) {
        with(binding) {
            Glide.with(pictureImageView.context)
                .load(picture.hdurl)
                .thumbnail(0.1f)
                .error(R.drawable.image_bg_default)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(pictureImageView)

            pictureTitleTv.text = picture.title
        }
    }
}