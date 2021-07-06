package ru.ekhalikov.homework2.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ekhalikov.homework2.R
import ru.ekhalikov.homework2.models.Movie
import ru.ekhalikov.homework2.presentation.moviedetails.MovieDetailsFragment
import ru.ekhalikov.homework2.presentation.movies.MovieListFragment

class MainActivity : AppCompatActivity(),
    MovieListFragment.MovieListItemClickListener,
    MovieDetailsFragment.MovieDetailsBackClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            routeToMoviesList()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size > 1) {
            routeBack()
        } else {
            finish()
        }
    }

    override fun onMovieSelected(movie: Movie) {
        routeToMovieDetails(movie)
    }

    override fun onMovieDeselected() {
        routeBack()
    }


    private fun routeToMoviesList() {
        supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                        MovieListFragment.newInstance(),
                        MovieListFragment::class.java.simpleName)
                .addToBackStack("trans:${MovieListFragment::class.java.simpleName}")
                .commit()
    }

    private fun routeToMovieDetails(movie: Movie) {
        supportFragmentManager.beginTransaction()
                .add(
                    R.id.container,
                        MovieDetailsFragment.newInstance(movie.id),
                        MovieDetailsFragment::class.java.simpleName)
                .addToBackStack("trans:${MovieDetailsFragment::class.java.simpleName}")
                .commit()
    }

    private fun routeBack() {
        supportFragmentManager.popBackStack()
    }
}