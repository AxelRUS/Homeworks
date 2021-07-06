package ru.ekhalikov.homework2.data.network.apis

import retrofit2.http.GET
import retrofit2.http.Query
import ru.ekhalikov.homework2.data.network.dto.MoviesResultDto

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): MoviesResultDto
}