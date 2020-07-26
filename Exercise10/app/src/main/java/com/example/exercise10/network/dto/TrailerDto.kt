package com.example.exercise10.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrailerDto(
    @Json(name = "key")
    val url: String
)

@JsonClass(generateAdapter = true)
data class TrailerList(
    @Json(name = "results")
    val results: List<TrailerDto>
)