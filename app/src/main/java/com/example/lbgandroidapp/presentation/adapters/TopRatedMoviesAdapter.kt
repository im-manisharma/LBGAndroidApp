package com.example.lbgandroidapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lbgandroidapp.R
import com.example.lbgandroidapp.data.entities.MovieResultDto
import com.example.lbgandroidapp.databinding.ListItemMovieBinding
import com.example.lbgandroidapp.utils.BASE_IMAGE_URL
import com.example.lbgandroidapp.utils.extentions.loadImage

class TopRatedMoviesAdapter(
    private val onClick: (Int) -> Unit
): ListAdapter<MovieResultDto, TopRatedMoviesAdapter.MoviesViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<MovieResultDto>() {
        override fun areItemsTheSame(oldItem: MovieResultDto, newItem: MovieResultDto) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieResultDto, newItem: MovieResultDto) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
       holder.bindData(getItem(position))
    }

    inner class MoviesViewHolder(private val binding: ListItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: MovieResultDto) {
            //binding.tvMovieName.text = data.title
            //binding.tvMovieReleaseDate.text = "Release Date: ${data.release_date}"
            //binding.tvRating.text = "Rating: ${data.vote_average}"
            //binding.tvRatingCount.text = "Rating Count: ${data.vote_count}"
            binding.imageView.loadImage("${BASE_IMAGE_URL}${data.poster_path}", R.drawable.ic_launcher_background)
            binding.root.setOnClickListener {
               onClick(data.id?:0)
            }
            binding.data = data
            binding.executePendingBindings()
        }
    }
}