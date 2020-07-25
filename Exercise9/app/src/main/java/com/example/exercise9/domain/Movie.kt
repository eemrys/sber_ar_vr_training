package com.example.exercise9.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    val id: String,
    val title: String,
    val poster: String,
    val posterWide: String,
    val summary: String,
    val releaseDate: String) : Parcelable