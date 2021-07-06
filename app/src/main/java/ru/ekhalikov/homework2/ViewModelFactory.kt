package ru.ekhalikov.homework2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.ekhalikov.homework2.data.MovieRepository
import ru.ekhalikov.homework2.presentation.moviedetails.MovieDetailsViewModel
import ru.ekhalikov.homework2.presentation.movies.MovieListViewModel

class ViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MovieListViewModel::class.java -> MovieListViewModel(repository)
            MovieDetailsViewModel::class.java -> MovieDetailsViewModel(repository)
            else -> throw IllegalArgumentException("ViewModel of class:$modelClass is not supported")
        } as T
    }
}