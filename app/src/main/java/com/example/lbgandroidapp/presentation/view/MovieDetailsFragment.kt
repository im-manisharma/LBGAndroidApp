package com.example.lbgandroidapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.lbgandroidapp.R
import com.example.lbgandroidapp.databinding.FragmentMovieDetailsBinding
import com.example.lbgandroidapp.domain.entities.MovieDetailsDomainModel
import com.example.lbgandroidapp.presentation.uistates.MovieDetailsUiState
import com.example.lbgandroidapp.presentation.viewmodel.MovieDetailsViewModel
import com.example.lbgandroidapp.utils.EXTRA_MOVIE_ID
import com.example.lbgandroidapp.utils.extentions.doGone
import com.example.lbgandroidapp.utils.extentions.doVisible
import com.example.lbgandroidapp.utils.extentions.loadImage
import com.example.lbgandroidapp.utils.extentions.showToastMsg
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inItObserver()

        arguments?.getInt(EXTRA_MOVIE_ID)?.run {
            viewModel.movieId = this
            viewModel.getMovieDetails()
        }
    }

    private fun inItObserver() {
        // Start a coroutine in the lifecycle scope
        viewLifecycleOwner.lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // Note that this happens when lifecycle is STARTED and stops
                // collecting when the lifecycle is STOPPED
                viewModel.uiState.collect { uiState ->
                    // New value received
                    when (uiState) {
                        is MovieDetailsUiState.Success -> {
                            populateUi(uiState.data)
                            binding.progressBarLayout.progressBar.doGone()
                        }
                        is MovieDetailsUiState.Error -> {
                            requireContext().showToastMsg(uiState.message)
                            binding.progressBarLayout.progressBar.doGone()
                        }
                        is MovieDetailsUiState.Loading -> {
                            binding.progressBarLayout.progressBar.doVisible()
                        }
                    }
                }
            }
        }
    }

    private fun populateUi(movieDetailsDomainModel: MovieDetailsDomainModel){
        movieDetailsDomainModel.let {
            binding.data = it
            binding.imageView.loadImage(it.image, R.drawable.ic_launcher_background)
            binding.executePendingBindings()
        }
    }
}