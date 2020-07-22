package com.example.exercise8.data

import com.example.exercise8.data.MovieItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieList (
    @Json(name = "results")
    val results: List<MovieItem>
)