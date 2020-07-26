package com.example.exercise9.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDto (
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "poster_path") val poster: String,
    @Json(name = "backdrop_path") val posterWide: String,
    @Json(name = "overview") val summary: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "popularity") val popularity: Double)

@JsonClass(generateAdapter = true)
data class MovieList (
    @Json(name = "results")
    val results: List<MovieDto>
)