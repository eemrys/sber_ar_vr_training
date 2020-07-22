package com.example.exercise8.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieList (
    val results: List<MovieItem>
)