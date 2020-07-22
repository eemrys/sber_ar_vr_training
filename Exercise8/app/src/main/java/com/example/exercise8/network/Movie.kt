package com.example.exercise8.network

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val title: String,
    @DrawableRes val poster: Int,
    @DrawableRes val posterWide: Int,
    val summary: String,
    val releaseDate: String,
    val trailerUrl: String) : Parcelable

@Parcelize
data class MovieItem (
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "poster_path") val poster: String,
    @Json(name = "backdrop_path") val posterWide: String,
    @Json(name = "overview") val summary: String,
    @Json(name = "release_date") val releaseDate: String) : Parcelable