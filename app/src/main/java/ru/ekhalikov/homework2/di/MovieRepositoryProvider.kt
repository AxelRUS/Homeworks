package ru.ekhalikov.homework2.di

import ru.ekhalikov.homework2.data.MovieRepository

interface MovieRepositoryProvider {
    fun provideMovieRepository(): MovieRepository
}