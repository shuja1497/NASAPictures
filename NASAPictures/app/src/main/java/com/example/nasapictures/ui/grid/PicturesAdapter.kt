package com.example.nasapictures.ui.grid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.nasapictures.databinding.ImageItemBinding
import com.example.nasapictures.model.Picture

class PicturesAdapter(private var pictures: MutableList<Picture>) :
    ListAdapter<Picture, PictureViewHolder>(PictureDiffCallback()) {

    fun updateData(newPictures: List<Picture>) {
        pictures.clear()
        pictures.addAll(newPictures)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = pictures.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(pictures[position])
    }

    class PictureDiffCallback : DiffUtil.ItemCallback<Picture>() {
        override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean =
            oldItem == newItem

    }
}