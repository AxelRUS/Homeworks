package ru.ekhalikov.homework2.network

import ru.ekhalikov.homework2.network.apis.MoviesApi

interface MoviesNetworkClient {
    fun moviesApi(): MoviesApi
}