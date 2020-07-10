package com.example.android.gdgfinder.search

import com.example.android.gdgfinder.network.GdgChapter

class GdgClickListener(val clickListener: (chapter: GdgChapter) -> Unit) {
    fun onClick(chapter: GdgChapter) = clickListener(chapter)
}