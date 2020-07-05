package com.example.android.trackmysleepquality.sleeptracker

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.android.synthetic.main.list_item_sleep_night.view.*

class ConstraintLayoutViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val res: Resources = itemView.context.resources
    private val sleepLength: TextView = itemView.txtvLength
    private val quality: TextView = itemView.txtvQuality
    private val qualityImage: ImageView = itemView.imgvQuality

    companion object {
        fun from(parent: ViewGroup): ConstraintLayoutViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                    .inflate(R.layout.list_item_sleep_night, parent, false)

            return ConstraintLayoutViewHolder(view)
        }
    }

    fun bind(item: SleepNight) {
        sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
        quality.text = convertNumericQualityToString(item.sleepQuality, res)
        qualityImage.setImageResource(when (item.sleepQuality) {
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            2 -> R.drawable.ic_sleep_2
            3 -> R.drawable.ic_sleep_3
            4 -> R.drawable.ic_sleep_4
            5 -> R.drawable.ic_sleep_5
            else -> R.drawable.ic_sleep_active
        })
    }
}