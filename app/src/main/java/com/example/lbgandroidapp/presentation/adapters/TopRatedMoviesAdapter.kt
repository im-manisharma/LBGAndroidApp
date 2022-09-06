package com.example.lbgandroidapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.lbgandroidapp.databinding.ListItemMovieBinding
import com.example.lbgandroidapp.domain.entities.MovieDomainModel

class TopRatedMoviesAdapter(
    private val onClick: (Int) -> Unit
): ListAdapter<MovieDomainModel, MoviesViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<MovieDomainModel>() {
        override fun areItemsTheSame(oldItem: MovieDomainModel, newItem: MovieDomainModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieDomainModel, newItem: MovieDomainModel) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ListItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val data = getItem(position)
        holder.bindData(data)
        holder.itemView.setOnClickListener {
            onClick(data.id?:0)
        }
    }
}