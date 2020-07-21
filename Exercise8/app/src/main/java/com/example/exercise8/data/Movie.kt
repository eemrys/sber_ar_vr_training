package com.example.exercise8.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val title: String,
    @DrawableRes val poster: Int,
    @DrawableRes val posterWide: Int,
    val summary: String,
    val releaseDate: String,
    val trailerUrl: String) : Parcelable