package com.example.android.trackmysleepquality.sleeptracker

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_sleep_night.*

class ConstraintLayoutViewHolder private constructor(override val containerView: View):
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

    private val res: Resources = containerView.context.resources

    companion object {
        fun from(parent: ViewGroup): ConstraintLayoutViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                    .inflate(R.layout.list_item_sleep_night, parent, false)

            return ConstraintLayoutViewHolder(view)
        }
    }

    fun bind(item: SleepNight) {
        txtvLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
        txtvQuality.text = convertNumericQualityToString(item.sleepQuality, res)
        imgvQuality.setImageResource(when (item.sleepQuality) {
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