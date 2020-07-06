package com.example.android.trackmysleepquality.sleeptracker

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.setIconImage
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_sleep_night.*

class ConstraintLayoutViewHolder private constructor(override val containerView: View):
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

    companion object {
        fun from(parent: ViewGroup): ConstraintLayoutViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                    .inflate(R.layout.list_item_sleep_night, parent, false)

            return ConstraintLayoutViewHolder(view)
        }
    }

    fun bind(clickListener: SleepNightListener, item: SleepNight) {
        val res: Resources = containerView.context.resources
        constLayout.setOnClickListener{ clickListener.onClick(item) }
        txtvQuality.text = convertNumericQualityToString(item.sleepQuality, res)
        imgvQuality.setIconImage(item.sleepQuality)
    }
}