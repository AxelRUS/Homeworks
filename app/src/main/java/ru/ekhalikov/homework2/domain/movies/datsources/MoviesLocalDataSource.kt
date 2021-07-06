package ru.ekhalikov.homework2.domain.movies.datsources

import ru.ekhalikov.homework2.data.storage.entities.JsonMovie


interface MoviesLocalDataSource {
    suspend fun getMoviesAsync(forceRefresh: Boolean = true): List<JsonMovie>
    suspend fun getMovieByIdAsync(movieId: Int, forceRefresh: Boolean = true): JsonMovie?
    suspend fun updateMovieAsync(movie: JsonMovie)
}