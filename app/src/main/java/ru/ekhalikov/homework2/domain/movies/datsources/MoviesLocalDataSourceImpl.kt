package ru.ekhalikov.homework2.domain.movies.datsources

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ekhalikov.homework2.data.JsonMovieRepository
import ru.ekhalikov.homework2.data.storage.entities.JsonMovie

class MoviesLocalDataSourceImpl(private val jsonLoader: JsonMovieRepository): MoviesLocalDataSource {

    private var cachedMovieEntities: MutableList<JsonMovie> = mutableListOf()

    override suspend fun getMoviesAsync(forceRefresh: Boolean): List<JsonMovie> =
        withContext(Dispatchers.IO) {
            if (cachedMovieEntities.isEmpty() || forceRefresh) {
                cachedMovieEntities = jsonLoader.loadMovies().toMutableList()
            }
            ArrayList(cachedMovieEntities)
        }

    override suspend fun getMovieByIdAsync(movieId: Int, forceRefresh: Boolean): JsonMovie? {
        TODO("Not yet implemented")
    }

    override suspend fun updateMovieAsync(movie: JsonMovie) {
        TODO("Not yet implemented")
    }
}