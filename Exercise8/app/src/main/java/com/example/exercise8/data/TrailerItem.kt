package com.example.exercise8.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrailerItem(
    @Json(name = "key")
    val url: String
)