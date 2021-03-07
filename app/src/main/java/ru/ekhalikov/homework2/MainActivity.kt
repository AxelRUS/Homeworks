package ru.ekhalikov.homework2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ekhalikov.homework2.data.JsonMovieRepository
import ru.ekhalikov.homework2.data.MovieRepository
import ru.ekhalikov.homework2.di.MovieRepositoryProvider
import ru.ekhalikov.homework2.model.Movie
import ru.ekhalikov.homework2.ui.moviedetails.MovieDetailsFragment
import ru.ekhalikov.homework2.ui.movies.MovieListFragment

class MainActivity : AppCompatActivity(),
        MovieListFragment.MovieListItemClickListener,
        MovieDetailsFragment.MovieDetailsBackClickListener,
        MovieRepositoryProvider {

    private val jsonMovieRepository = JsonMovieRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            routeToMoviesList()
        }
    }

    override fun onMovieSelected(movie: Movie) {
        routeToMovieDetails(movie)
    }

    override fun onMovieDeselected() {
        routeToMoviesList()
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

    override fun provideMovieRepository(): MovieRepository = jsonMovieRepository
}