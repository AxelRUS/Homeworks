package ru.ekhalikov.homework2.data.network.dto

import kotlinx.serialization.SerialName

data class GenresResultDto (
    @SerialName("cast")
    val genres: List<GenreDto> = emptyList()
)