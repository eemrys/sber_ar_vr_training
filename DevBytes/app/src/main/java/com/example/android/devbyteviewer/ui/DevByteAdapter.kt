package com.example.android.devbyteviewer.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.devbyteviewer.R
import com.example.android.devbyteviewer.domain.Video
import com.example.android.devbyteviewer.util.setImageUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.devbyte_item.*

class VideoClick(val block: (Video) -> Unit) {
    fun onClick(video: Video) = block(video)
}

class DevByteAdapter(private val callback: VideoClick) : RecyclerView.Adapter<DevByteAdapter.DevByteViewHolder>() {
    var videos: List<Video> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevByteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.devbyte_item, parent, false)
        return DevByteViewHolder(view)
    }

    override fun getItemCount() = videos.size

    override fun onBindViewHolder(holder: DevByteViewHolder, position: Int) {
        holder.bind(callback, videos[position])
    }

    inner class DevByteViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(callback: VideoClick, video: Video) {
            txtvTitle.text = video.title
            txtvDescription.text = video.shortDescription
            setImageUrl(imgvVideoThumbnail, video.thumbnail)
            viewClickableOverlay.setOnClickListener {
                callback.onClick(video)
            }
        }
    }
}