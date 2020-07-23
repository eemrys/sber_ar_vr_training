package com.example.exercise8.movieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise8.R
import com.example.exercise8.data.MovieItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_view.*
import coil.api.load

class MoviesAdapter(private val clickListener: (position: Int) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var data = listOf<MovieItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MovieViewHolder (private val clickListener: (position: Int) -> Unit,
                           override val containerView: View):

        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(movie: MovieItem) {
            containerView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener(position)
                }
            }
            movie.apply {
                txtvTitle.text = title
                txtvSummary.text = summary
                imgvPoster.load(poster) {
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_broken_image)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_view, parent, false)
        return MovieViewHolder(clickListener, view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position])
    }
}