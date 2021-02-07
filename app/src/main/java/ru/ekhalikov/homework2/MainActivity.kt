package ru.ekhalikov.homework2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ekhalikov.homework2.data.JsonMovieRepository
import ru.ekhalikov.homework2.data.MovieRepository
import ru.ekhalikov.homework2.di.MovieRepositoryProvider
import ru.ekhalikov.homework2.ui.moviedetails.MovieDetailsFragment
import ru.ekhalikov.homework2.ui.movies.MovieListFragment

class MainActivity : AppCompatActivity(),
        MovieListFragment.OnCardClick,
        MovieDetailsFragment.BackButtonListener,
        MovieRepositoryProvider {

    private val jsonMovieRepository = JsonMovieRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MovieListFragment.newInstance())
                    .commit()
        }
    }

    override fun onClick() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieDetailsFragment.newInstance())
                .commit()
    }

    override fun onBackButtonPressed() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieListFragment.newInstance())
                .commit()
    }

    override fun provideMovieRepository(): MovieRepository = jsonMovieRepository
}