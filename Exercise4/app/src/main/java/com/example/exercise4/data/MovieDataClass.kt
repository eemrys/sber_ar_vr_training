package com.example.exercise4.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val title: String,
                 val summary: String,
                 @DrawableRes val poster: Int,
                 val trailerUrl: String) : Parcelable