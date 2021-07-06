package ru.ekhalikov.homework2.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ekhalikov.homework2.data.MovieRepository
import ru.ekhalikov.homework2.models.Movie

class MovieListViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>> = _movies

    fun onViewCreated() {
        viewModelScope.launch {
            _movies.postValue(repository.loadMovies())
        }
    }

}