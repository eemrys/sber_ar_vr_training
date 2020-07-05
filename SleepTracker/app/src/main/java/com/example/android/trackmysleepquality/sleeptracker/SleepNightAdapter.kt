package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.R

class SleepNightAdapter: RecyclerView.Adapter<ConstraintLayoutViewHolder>() {
    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConstraintLayoutViewHolder {
        return ConstraintLayoutViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ConstraintLayoutViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }
}