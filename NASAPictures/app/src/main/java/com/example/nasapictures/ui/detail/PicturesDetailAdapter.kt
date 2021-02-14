package com.example.nasapictures.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.nasapictures.databinding.DetailPictureItemBinding
import com.example.nasapictures.model.Picture

class PicturesDetailAdapter(private var pictures: MutableList<Picture>) :
    ListAdapter<Picture, PictureDetailViewHolder>(PictureDiffCallback()) {

    fun updateData(newPictures: List<Picture>) {
        pictures.clear()
        pictures.addAll(newPictures)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = pictures.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureDetailViewHolder {
        val binding =
            DetailPictureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureDetailViewHolder, position: Int) {
        holder.bind(pictures[position])
    }

    class PictureDiffCallback : DiffUtil.ItemCallback<Picture>() {
        override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean =
            oldItem == newItem

    }
}