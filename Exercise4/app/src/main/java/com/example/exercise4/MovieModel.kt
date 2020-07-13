package com.example.exercise4

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val title: String,
                 val summary: String,
                 val posterId: Int) : Parcelable