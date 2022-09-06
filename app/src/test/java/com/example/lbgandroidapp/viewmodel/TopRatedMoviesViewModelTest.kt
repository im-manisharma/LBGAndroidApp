package com.example.lbgandroidapp.viewmodel

import com.example.lbgandroidapp.data.dto.MovieResultDto
import com.example.lbgandroidapp.domain.entities.MovieDomainModel
import com.example.lbgandroidapp.domain.usecases.GetTopRatedMoviesUseCase
import com.example.lbgandroidapp.presentation.uistates.TopRatedMoviesUiState
import com.example.lbgandroidapp.presentation.viewmodel.TopRatedMoviesViewModel
import com.example.lbgandroidapp.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TopRatedMoviesViewModelTest {
    private val testDispatcher = UnconfinedTestDispatcher()
    lateinit var mTopRatedMoviesViewModel: TopRatedMoviesViewModel
    private val mGetTopRatedMoviesUseCase=  mockk<GetTopRatedMoviesUseCase>(relaxed = true)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        mTopRatedMoviesViewModel = TopRatedMoviesViewModel(
            mGetTopRatedMoviesUseCase,
            testDispatcher,
            "api_key"
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
    }

    @Test
    fun `when apiCalled, return success`() = runBlocking {
        coEvery { mGetTopRatedMoviesUseCase.invoke("api_key") } returns Result.Success(listOf(
            MovieDomainModel(title = "abcd"),
            MovieDomainModel(title = "efgh")
        ))

        mTopRatedMoviesViewModel.getTopRatedMovies()
        assert(mTopRatedMoviesViewModel.uiState.value is TopRatedMoviesUiState.Success)
    }

    @Test
    fun `when apiCalled, return error`() = runBlocking {
        coEvery { mGetTopRatedMoviesUseCase.invoke("api_key") } returns Result.Error(Exception("error message"))
        mTopRatedMoviesViewModel.getTopRatedMovies()
        assert(mTopRatedMoviesViewModel.uiState.value is TopRatedMoviesUiState.Error)
    }
}