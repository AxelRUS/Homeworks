package ru.ekhalikov.homework2.domain.movies.models

import java.io.Serializable

data class Actor(
        val id: Int,
        val name: String,
        val imageUrl: String,
) : Serializable