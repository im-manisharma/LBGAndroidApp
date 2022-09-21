package com.example.lbgandroidapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.lbgandroidapp.presentation.adapters.MoviesViewHolder
import com.example.lbgandroidapp.presentation.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TopRatedMoviesFragTest {

    @Test
    fun isTopRatedMoviesFragmentVisible_onAppLaunch(){
        onView(withId(R.id.rvTopRatedMovies)).check(matches(isDisplayed()))
    }

    @Test
    fun selectItem_andNavigateToMovieDetailsFrag() {
        onView(withId(R.id.rvTopRatedMovies))
            .perform(actionOnItemAtPosition<MoviesViewHolder>(0, click()))

        onView(withId(R.id.tvMovieName)).check(matches(withText("The Shawshank Redemption")))
    }

    @Test
    fun selectItem_andNavigateToMovieDetailsFrag_pressBack() {
        onView(withId(R.id.rvTopRatedMovies))
            .perform(actionOnItemAtPosition<MoviesViewHolder>(0, click()))

        onView(withId(R.id.tvMovieName)).check(matches(withText("The Shawshank Redemption")))

        pressBack()
        onView(withId(R.id.rvTopRatedMovies)).check(matches(isDisplayed()))
    }
}