package com.example.lbgandroidapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.lbgandroidapp.R
import com.example.lbgandroidapp.databinding.FragmentTopRatedMoviesBinding
import com.example.lbgandroidapp.domain.entities.MovieDomainModel
import com.example.lbgandroidapp.presentation.adapters.TopRatedMoviesAdapter
import com.example.lbgandroidapp.presentation.uistates.TopRatedMoviesUiState
import com.example.lbgandroidapp.presentation.viewmodel.TopRatedMoviesViewModel
import com.example.lbgandroidapp.utils.EXTRA_MOVIE_ID
import com.example.lbgandroidapp.utils.extentions.doGone
import com.example.lbgandroidapp.utils.extentions.doVisible
import com.example.lbgandroidapp.utils.extentions.showToastMsg
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopRatedMoviesFragment : Fragment() {
    private lateinit var binding: FragmentTopRatedMoviesBinding
    private val viewModel: TopRatedMoviesViewModel by viewModels()
    private lateinit var topRatedMoviesAdapter: TopRatedMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTopRatedMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inItAdapter()
        inItObserver()
    }

    private fun inItAdapter(){
        topRatedMoviesAdapter = TopRatedMoviesAdapter {
            findNavController().navigate(
                R.id.action_topRatedMoviesFragment_to_movieDetailsFragment,
                bundleOf(EXTRA_MOVIE_ID to it)
            )
        }
        binding.rvTopRatedMovies.adapter = topRatedMoviesAdapter
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
                        is TopRatedMoviesUiState.Success -> {
                            binding.progressBarLayout.progressBar.doGone()
                            showAnimeUI(uiState.movieList)
                        }
                        is TopRatedMoviesUiState.Error -> {
                            requireContext().showToastMsg(uiState.message)
                            binding.progressBarLayout.progressBar.doGone()
                        }
                        is TopRatedMoviesUiState.Loading -> {
                            binding.progressBarLayout.progressBar.doVisible()
                        }
                    }
                }
            }
        }
    }

    private fun showAnimeUI(animeList: List<MovieDomainModel>) {
        topRatedMoviesAdapter.submitList(animeList)
    }
}