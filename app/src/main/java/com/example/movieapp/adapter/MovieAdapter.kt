package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.ItemLayoutBinding
import com.example.movieapp.model.ResponseItemItem

class MovieAdapter : ListAdapter<ResponseItemItem, MovieAdapter.MovieViewHolder>(DiffCallBack()) {

    private class DiffCallBack : DiffUtil.ItemCallback<ResponseItemItem>() {
        override fun areItemsTheSame(
            oldItem: ResponseItemItem,
            newItem: ResponseItemItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseItemItem,
            newItem: ResponseItemItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ResponseItemItem) {
            binding.apply {
                Glide.with(binding.imageView)
                    .load(data.image.original)
                    .into(binding.imageView)

                textMovieName.text = data.name

                try {
                    text1.text = "${data.genres[0]},"
                    text2.text = "${data.genres[1]},"
                    text3.text = data.genres[2]
                } catch (e: IndexOutOfBoundsException) {
                    e.printStackTrace()
                }
            }
        }
    }
}