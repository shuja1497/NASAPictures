package com.example.nasapictures.ui.detail

import androidx.recyclerview.widget.RecyclerView
import com.example.nasapictures.databinding.DetailPictureItemBinding
import com.example.nasapictures.model.Picture

class PictureDetailViewHolder(private val binding: DetailPictureItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(picture: Picture) {
        binding.picture = picture
    }
}