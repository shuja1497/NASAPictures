package com.example.nasapictures.ui.grid

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasapictures.R
import com.example.nasapictures.databinding.ImageItemBinding
import com.example.nasapictures.model.Picture

class PictureViewHolder(private val binding: ImageItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(picture: Picture) {
        with(binding) {
            Glide.with(pictureImageView.context)
                .load(picture.url)
                .thumbnail(0.1f)
                .error(R.drawable.image_bg_default)
                .into(pictureImageView)

            pictureTitleTv.text = picture.title
        }
    }
}