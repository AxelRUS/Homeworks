package ru.ekhalikov.homework2.presentation.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ekhalikov.homework2.data.MovieRepository
import ru.ekhalikov.homework2.domain.movies.models.Movie

class MovieDetailsViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movie: MutableLiveData<Movie> = MutableLiveData()
    val movie: LiveData<Movie> = _movie

    fun onViewCreated(movieId: Int) {
        viewModelScope.launch {
            _movie.postValue(repository.loadMovie(movieId))
        }
    }
}