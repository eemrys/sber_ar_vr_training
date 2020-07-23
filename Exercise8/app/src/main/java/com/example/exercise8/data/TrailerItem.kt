package com.example.exercise8.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrailerItem(
    @Json(name = "key")
    val url: String
)

@JsonClass(generateAdapter = true)
data class TrailerList(
    @Json(name = "results")
    val results: List<TrailerItem>
)