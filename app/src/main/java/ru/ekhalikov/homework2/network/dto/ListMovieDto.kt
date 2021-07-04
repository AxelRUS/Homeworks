package ru.ekhalikov.homework2.network.dto

import kotlinx.serialization.SerialName

data class ListMovieDto(
    @SerialName("id")
    val id: Int = 0,

    @SerialName("title")
    val title: String = "",

    @SerialName("overview")
    val overview: String = ""
)
