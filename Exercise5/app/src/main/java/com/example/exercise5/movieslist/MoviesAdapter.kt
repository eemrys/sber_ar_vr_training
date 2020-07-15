package com.example.exercise5.movieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise5.R
import com.example.exercise5.data.Movie
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_view.*

class MoviesAdapter(private val clickListener: (position: Int) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var data = emptyList<Movie>()

    class MovieViewHolder (private val clickListener: (position: Int) -> Unit,
                           override val containerView: View):

        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(movie: Movie) {
            containerView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener(position)
                }
            }
            txtvTitle.text = movie.title
            txtvSummary.text = movie.summary
            imgvPoster.setImageResource(movie.poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_view, parent, false)
        return MovieViewHolder(
            clickListener,
            view
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position])
    }
}