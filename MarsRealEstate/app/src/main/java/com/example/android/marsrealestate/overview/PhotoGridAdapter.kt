/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.bindImage
import com.example.android.marsrealestate.network.MarsProperty
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.grid_view_item.*

class PhotoGridAdapter(private val clickListener: GridClickListener) :
        ListAdapter<MarsProperty, PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
         override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
             return oldItem === newItem
         }

         override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
             return oldItem.id == newItem.id
         }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPropertyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.grid_view_item, parent, false)
        return MarsPropertyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(clickListener, marsProperty)
    }

    inner class MarsPropertyViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(clickListener: GridClickListener, item: MarsProperty) {
            containerView.setOnClickListener{ clickListener.onClick(item) }
            bindImage(imgvMars, item.imgSrcUrl)
        }
    }
}
