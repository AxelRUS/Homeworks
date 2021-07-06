package ru.ekhalikov.homework2.data.network

import ru.ekhalikov.homework2.data.network.apis.MoviesApi

interface MoviesNetworkClient {
    fun moviesApi(): MoviesApi
}