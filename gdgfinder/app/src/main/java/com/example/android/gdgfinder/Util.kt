package com.example.android.gdgfinder

import android.view.View
import com.example.android.gdgfinder.network.GdgChapter

fun View.showOnlyWhenEmpty(data: List<GdgChapter>?) {
    visibility = when {
        data == null || data.isEmpty() -> View.VISIBLE
        else -> View.GONE
    }
}