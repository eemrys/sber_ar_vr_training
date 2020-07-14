package com.example.exercise4

import android.widget.ImageView

fun ImageView.setMoviePoster(posterId: Int) {
    this.setImageResource(when(posterId) {
        0 -> R.drawable.poster_0
        1 -> R.drawable.poster_1
        2 -> R.drawable.poster_2
        3 -> R.drawable.poster_3
        4 -> R.drawable.poster_4
        5 -> R.drawable.poster_5
        6 -> R.drawable.poster_6
        7 -> R.drawable.poster_7
        else -> R.drawable.poster_8
    })
}