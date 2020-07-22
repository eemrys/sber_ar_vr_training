package com.example.exercise8.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieItem (
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "poster_path") val poster: String,
    @Json(name = "backdrop_path") val posterWide: String,
    @Json(name = "overview") val summary: String,
    @Json(name = "release_date") val releaseDate: String) : Parcelable