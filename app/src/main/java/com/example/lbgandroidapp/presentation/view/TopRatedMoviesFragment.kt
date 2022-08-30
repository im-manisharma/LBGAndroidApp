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
import com.example.lbgandroidapp.data.entities.MovieResultDto
import com.example.lbgandroidapp.databinding.FragmentTopRatedMoviesBinding
import com.example.lbgandroidapp.presentation.adapters.TopRatedMoviesAdapter
import com.example.lbgandroidapp.presentation.viewmodel.TopRatedMoviesUiState
import com.example.lbgandroidapp.presentation.viewmodel.TopRatedMoviesViewModel
import com.example.lbgandroidapp.utils.extentions.doGone
import com.example.lbgandroidapp.utils.extentions.doVisible
import com.example.lbgandroidapp.utils.extentions.showToastMsg
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopRatedMoviesFragment : Fragment() {
    private lateinit var mBinding: FragmentTopRatedMoviesBinding
    private val mViewModel: TopRatedMoviesViewModel by viewModels()
    private lateinit var mTopRatedMoviesAdapter: TopRatedMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentTopRatedMoviesBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inItAdapter()
        inItObserver()
    }

    private fun inItAdapter(){
        mTopRatedMoviesAdapter = TopRatedMoviesAdapter {
            findNavController().navigate(
                R.id.action_topRatedMoviesFragment_to_movieDetailsFragment,
                bundleOf("movie_id" to it)
            )
        }
        mBinding.rvTopRatedMovies.adapter = mTopRatedMoviesAdapter
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
                mViewModel.uiState.collect { uiState ->
                    // New value received
                    when (uiState) {
                        is TopRatedMoviesUiState.Success -> {
                            mBinding.progressBarLayout.progressBar.doGone()
                            showAnimeUI(uiState.movieList)
                        }
                        is TopRatedMoviesUiState.Error -> {
                            requireContext().showToastMsg(uiState.message)
                            mBinding.progressBarLayout.progressBar.doGone()
                        }
                        is TopRatedMoviesUiState.Loading -> {
                            mBinding.progressBarLayout.progressBar.doVisible()
                        }
                    }
                }
            }
        }
    }

    private fun showAnimeUI(animeList: List<MovieResultDto>) {
        mTopRatedMoviesAdapter.submitList(animeList)
    }
}