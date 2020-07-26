package com.example.exercise9.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Movie (
    @PrimaryKey
    val id: String,
    val title: String,
    val poster: String,
    val posterWide: String,
    val summary: String,
    val releaseDate: String,
    val popularity: Double) : Parcelable