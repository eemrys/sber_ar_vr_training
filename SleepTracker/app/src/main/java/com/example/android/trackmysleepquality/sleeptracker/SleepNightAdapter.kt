package com.example.android.trackmysleepquality.sleeptracker

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter(private val clickListener: SleepNightListener)
    : ListAdapter<SleepNight, ConstraintLayoutViewHolder>(SleepNightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConstraintLayoutViewHolder {
        return ConstraintLayoutViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ConstraintLayoutViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }
}