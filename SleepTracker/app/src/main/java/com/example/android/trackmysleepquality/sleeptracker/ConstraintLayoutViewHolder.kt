package com.example.android.trackmysleepquality.sleeptracker

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_sleep_night.view.*

class ConstraintLayoutViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    val sleepLength: TextView = itemView.txtvLength
    val quality: TextView = itemView.txtvQuality
    val qualityImage: ImageView = itemView.imgvQuality
}