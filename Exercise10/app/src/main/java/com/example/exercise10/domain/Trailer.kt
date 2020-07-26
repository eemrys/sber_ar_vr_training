package com.example.exercise10.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Trailer (
    @PrimaryKey
    val movieId: String,
    val url: String
)