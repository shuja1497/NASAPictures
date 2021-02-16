package com.example.nasapictures.ui.grid

import androidx.recyclerview.widget.RecyclerView
import com.example.nasapictures.databinding.PictureItemBinding
import com.example.nasapictures.model.Picture

class PictureViewHolder(private val binding: PictureItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        picture: Picture,
        clickListener: (Picture) -> Unit
    ) {
        binding.picture = picture
        binding.root.setOnClickListener { clickListener(picture) }
    }
}