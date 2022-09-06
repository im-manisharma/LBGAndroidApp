package com.example.lbgandroidapp.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.lbgandroidapp.databinding.ListItemMovieBinding
import com.example.lbgandroidapp.domain.entities.MovieDomainModel

class MoviesViewHolder(
    private val binding: ListItemMovieBinding,
): RecyclerView.ViewHolder(binding.root) {
    fun bindData(data: MovieDomainModel) {
        binding.data = data
        binding.executePendingBindings()
    }
}